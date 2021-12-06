package com.smxy.marketprovider.service.Impl;

import com.smxy.marketapi.pojo.User;
import com.smxy.marketprovider.dao.UserMapper;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Random;

public class UserServiceImplTest {

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_userInsert(){
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for(int i=0;i<4;i++)
            code.append(random.nextInt(10));
        System.out.println(code.toString());
    }


    private User userDTO(){
        return User.builder()
                .phone("13900000000")
                .name("不吃香菜")
                .sno("20180861228")
                .createTime(new Date()).build();
    }

}
