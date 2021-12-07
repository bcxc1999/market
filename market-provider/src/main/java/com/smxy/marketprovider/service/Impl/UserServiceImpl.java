package com.smxy.marketprovider.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smxy.marketapi.pojo.User;
import com.smxy.marketapi.pojo.UserPwd;
import com.smxy.marketapi.service.UserService;
import com.smxy.marketprovider.dao.UserMapper;
import com.smxy.marketprovider.dao.UserPwdMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    UserPwdMapper userPwdMapper;

    public User queryByPhone(String phone){
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone,phone));
    }

    @Transactional
    public int register(String username,String phone, String pwd) {
        userMapper.insert(User.builder()
                .phone(phone)
                .name(username)
                .createTime(new Date()).build());
        userPwdMapper.insert(UserPwd.builder()
                .phone(phone)
                .pwd(pwd).build());
        return 0;
    }

}
