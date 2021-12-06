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

    
}
