package com.smxy.marketprovider.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smxy.marketapi.pojo.UserPwd;
import com.smxy.marketapi.service.UserPwdService;
import com.smxy.marketprovider.dao.UserPwdMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Service(interfaceClass = UserPwdService.class)
public class UserPwdServiceImpl implements UserPwdService {

    @Resource
    UserPwdMapper userPwdMapper;

    @Override
    public UserPwd queryByPhone(String phone) {
        return userPwdMapper.selectOne(new LambdaQueryWrapper<UserPwd>()
                .eq(UserPwd::getPhone,phone));
    }
}
