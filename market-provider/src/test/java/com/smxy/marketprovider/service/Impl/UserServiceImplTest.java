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
       when(userMapper.insert(any())).thenReturn(NumberUtils.INTEGER_ZERO);
       int i = userService.userInsert(userDTO());
       Assert.assertEquals(0,0);
        verify(userMapper).insert(any());
    }

    @Test
    public void test_userInfo(){
        User u = userDTO();
        when(userMapper.selectOne(any())).thenReturn(u);
        User user = userService.userInfo("20180861227");
        verify(userMapper).selectOne(any());
        Assert.assertEquals(u,user);
       // Assert.assertTrue();
    }

    private User userDTO(){
        return User.builder()
                .phone("13900000000")
                .name("不吃香菜")
                .sno("20180861228")
                .createTime(new Date()).build();
    }

}
