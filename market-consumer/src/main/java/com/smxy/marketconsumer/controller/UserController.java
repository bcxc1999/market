package com.smxy.marketconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.smxy.marketapi.pojo.User;
import com.smxy.marketapi.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("/userInsert")
    public @ResponseBody String UserInsert(){
        int i = userService.userInsert(User.builder()
                .phone("13900000002")
                .name("不吃香菜")
                .sno("20180861221")
                .createTime(new Date()).build());
        if(i>0)
            return "success";
        else
            return "erro";
    }

    @RequestMapping("/userDetail/{sno}")
    public @ResponseBody User UserDetail(@PathVariable("sno") String sno){
        return userService.userInfo(sno);
    }
}
