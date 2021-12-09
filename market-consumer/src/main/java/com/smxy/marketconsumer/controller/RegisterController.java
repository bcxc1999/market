package com.smxy.marketconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.smxy.marketapi.service.UserService;
import com.smxy.marketconsumer.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
public class RegisterController extends BaseController{
    private final static int TIME_OUT = 1000 * 60 * 5;

    @Autowired
    CacheService cacheService;
    @Reference
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("")
    public String register(@RequestParam("username") String username,
                           @RequestParam("sno") String sno,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("verificationCode") String verificationCode){
        if(isExist(phone)){
            delCode(phone);
            return "该手机已被注册，请勿重复注册";
        }
        if(verificationCode.equals(cacheService.get(phone))){
            delCode(phone);
            userService.register(username,sno,phone, passwordEncoder.encode(password));
            return "注册成功";
        }
        return "验证码错误";
    }


    /**
     * 是否存在该手机用户
     * @param phone
     * @return
     */
    public boolean isExist(String phone){
        return userService.queryByPhone(phone) != null;
    }

    /**
     * 使用完验证码 做删除处理 避免一个验证码多次操作
     * @param phone
     */
    private void delCode(String phone){
        if(cacheService.hasKey(phone)){
            cacheService.delete(phone);
        }
    }
}
