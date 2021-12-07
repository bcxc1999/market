package com.smxy.marketconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.smxy.marketapi.service.UserService;
import com.smxy.marketconsumer.service.CacheService;
import com.smxy.marketconsumer.utils.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final static int TIME_OUT = 1000 * 60 * 5;

    @Autowired
    CacheService cacheService;
    @Reference
    UserService userService;
    @Resource
    SendMessage sendMessage;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("")
    public String register(@RequestParam("username") String username,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("verificationCode") String verificationCode){
        if(isExist(phone))
            return "该手机已被注册，请勿重复注册";
        if(verificationCode.equals(cacheService.get(phone))){
            userService.register(username,phone, passwordEncoder.encode(password));
            return "注册成功";
        }
        return "验证码错误";
    }

    @RequestMapping("/getCode/{phone}")
    public String getCode(@PathVariable("phone") String phone){
        if(cacheService.hasKey(phone)){
            return "请勿重复申请验证码";
        }
        String code = sendMessage.randomVerificationCode();
        cacheService.add(phone,code,TIME_OUT, TimeUnit.MILLISECONDS);
        try {
            sendMessage.send(phone,code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "验证码成功发送";
    }

    /**
     * 是否存在该手机用户
     * @param phone
     * @return
     */
    public boolean isExist(String phone){
        return userService.queryByPhone(phone) != null;
    }
}
