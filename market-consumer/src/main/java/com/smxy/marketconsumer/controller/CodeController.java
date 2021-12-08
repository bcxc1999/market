package com.smxy.marketconsumer.controller;

import com.smxy.marketconsumer.service.CacheService;
import com.smxy.marketconsumer.utils.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class CodeController {

    private final static int TIME_OUT = 1000 * 60 * 5;

    @Resource
    SendMessage sendMessage;

    @Autowired
    CacheService cacheService;


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
}
