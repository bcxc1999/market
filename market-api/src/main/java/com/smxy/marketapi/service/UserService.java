package com.smxy.marketapi.service;

import com.smxy.marketapi.pojo.User;

public interface UserService {

     User queryByPhone(String phone);
     int register(String username,String phone,String pwd,String verificationCode);

}
