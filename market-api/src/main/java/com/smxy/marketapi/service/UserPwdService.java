package com.smxy.marketapi.service;

import com.smxy.marketapi.pojo.UserPwd;

public interface UserPwdService {

    UserPwd queryByPhone(String phone);
    int updateByPhone(UserPwd userPwd);

}
