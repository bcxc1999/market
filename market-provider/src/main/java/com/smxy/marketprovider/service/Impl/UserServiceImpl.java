package com.smxy.marketprovider.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smxy.marketapi.pojo.User;
import com.smxy.marketapi.service.UserService;
import com.smxy.marketprovider.dao.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    public int userInsert(User user){
        return userMapper.insert(user);
    }

    public User userInfo(String sno){
        return userMapper.selectOne(new LambdaQueryWrapper<User>().
                eq(User::getSno,sno));
    }

    public int userUpdateById(User user){
        return userMapper.updateById(user);
    }
}
