package com.smxy.marketprovider.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smxy.marketapi.bean.UserPwdDTO;
import com.smxy.marketapi.service.UserPwdService;
import com.smxy.marketprovider.dao.UserPwdMapper;
import com.smxy.marketprovider.pojo.UserPwd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Service(interfaceClass = UserPwdService.class)
public class UserPwdServiceImpl implements UserPwdService {

    @Resource
    UserPwdMapper userPwdMapper;

    @Override
    public UserPwdDTO queryByPhone(String phone) {
        UserPwd userPwd = userPwdMapper.selectOne(new LambdaQueryWrapper<UserPwd>()
                .eq(UserPwd::getPhone,phone));
        return convertUserPwdDTO(userPwd);
    }

    @Override
    public int updateByPhone(UserPwdDTO userPwdDTO) {
        UserPwd userPwd = convertUserPwd(userPwdDTO);
        userPwd.setModify(new Date());
        return userPwdMapper.update(userPwd,new LambdaQueryWrapper<UserPwd>()
                .eq(UserPwd::getPhone,userPwd.getPhone()));
    }

    private UserPwd convertUserPwd(UserPwdDTO userPwdDTO){
        return UserPwd.builder()
                .phone(userPwdDTO.getPhone())
                .pwd(userPwdDTO.getPwd()).build();
    }

    private UserPwdDTO convertUserPwdDTO(UserPwd userPwd){
        return UserPwdDTO.builder()
                .phone(userPwd.getPhone())
                .pwd(userPwd.getPwd()).build();
    }
}
