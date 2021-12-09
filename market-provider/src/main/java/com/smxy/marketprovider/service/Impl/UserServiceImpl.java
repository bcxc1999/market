package com.smxy.marketprovider.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smxy.marketapi.bean.UserVO;
import com.smxy.marketapi.service.UserService;
import com.smxy.marketprovider.dao.UserMapper;
import com.smxy.marketprovider.dao.UserPwdMapper;
import com.smxy.marketprovider.pojo.User;
import com.smxy.marketprovider.pojo.UserPwd;
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

    @Override
    public UserVO queryBySno(String sno) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone,sno));
        return convertUserVO(user);
    }

    public UserVO queryByPhone(String phone){
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone,phone));
        return convertUserVO(user);
    }

    @Override
    public int updateByPhone(UserVO userVO) {
        User user = convertUser(userVO);
        user.setModify(new Date());
        return userMapper.update(user,new LambdaQueryWrapper<User>()
                .eq(User::getPhone,user.getPhone()));
    }

    @Transactional
    public int register(String username,String sno,String phone, String pwd) {
        return  userMapper.insert(User.builder()
                .phone(phone)
                .name(username)
                .sno(sno)
                .createTime(new Date()).build())
                +
                userPwdMapper.insert(UserPwd.builder()
                .phone(phone)
                .pwd(pwd).build());
    }

    private User convertUser(UserVO userVO){
        return User.builder()
                .phone(userVO.getPhone())
                .sex(userVO.getSex())
                .avatar(userVO.getAvatar())
                .clazz(userVO.getClazz())
                .name(userVO.getName())
                .rName(userVO.getRName()).build();
    }

    private UserVO convertUserVO(User user){
        return UserVO.builder()
                .phone(user.getPhone())
                .sex(user.getSex())
                .avatar(user.getAvatar())
                .clazz(user.getClazz())
                .name(user.getName())
                .rName(user.getRName()).build();
    }

}
