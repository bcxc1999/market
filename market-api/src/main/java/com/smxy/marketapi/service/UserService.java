package com.smxy.marketapi.service;

import com.smxy.marketapi.pojo.User;
import com.smxy.marketapi.pojo.UserPwd;

public interface UserService {

     User queryByPhone(String phone);
     int register(String username,String phone,String pwd);

}
