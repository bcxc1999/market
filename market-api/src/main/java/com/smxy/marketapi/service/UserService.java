package com.smxy.marketapi.service;

import com.smxy.marketapi.bean.UserVO;

public interface UserService {

     UserVO queryBySno(String sno);
     UserVO queryByPhone(String phone);
     int updateByPhone(UserVO user);
     int register(String username,String sno,String phone,String pwd);

}
