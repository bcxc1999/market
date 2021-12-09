package com.smxy.marketprovider.service.Impl;

import com.smxy.marketprovider.dao.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void myTest(){
        System.out.println(System.currentTimeMillis());
    }



}
