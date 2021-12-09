package com.smxy.marketapi.service;

import com.smxy.marketapi.bean.UserPwdDTO;

public interface UserPwdService {

    UserPwdDTO queryByPhone(String phone);
    int updateByPhone(UserPwdDTO userPwd);

}
