package com.smxy.marketconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.smxy.marketapi.pojo.UserPwd;
import com.smxy.marketapi.service.UserPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Reference
    UserPwdService userPwdService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone,
                        @RequestParam("password") String pwd){
        UserPwd userPwd = userPwdService.queryByPhone(phone);
        if(userPwd == null){
            return "该账户不存在";
        }
        if(passwordEncoder.matches(pwd,userPwd.getPwd())){
            return "登陆成功";
        }
        return "密码错误";
    }


}
