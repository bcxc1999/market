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



}
