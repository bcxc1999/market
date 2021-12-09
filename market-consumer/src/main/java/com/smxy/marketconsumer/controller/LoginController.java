package com.smxy.marketconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.smxy.marketapi.bean.UserPwdDTO;
import com.smxy.marketapi.service.UserPwdService;
import com.smxy.marketconsumer.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController extends BaseController{

    @Reference
    UserPwdService userPwdService;
    @Resource
    CacheService cacheService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone,
                        @RequestParam("password") String pwd){
        UserPwdDTO userPwdDTO = userPwdService.queryByPhone(phone);
        if(userPwdDTO == null){
            return "该账户不存在";
        }
        if(passwordEncoder.matches(pwd,userPwdDTO.getPwd())){
            cacheService.add("token_"+phone,tokenUtil.getToken(phone));
            return "登陆成功";
        }
        return "密码错误";
    }


}
