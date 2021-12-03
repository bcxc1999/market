package com.smxy.marketapi.service;

import com.smxy.marketapi.pojo.User;

public interface UserService {
     int userInsert(User user);
     User userInfo(String sno);

}
