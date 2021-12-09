package com.smxy.marketconsumer.controller;

import com.smxy.marketconsumer.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    TokenUtil tokenUtil;
}
