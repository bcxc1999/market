package com.smxy.marketconsumer.filter;

import com.smxy.marketconsumer.exception.TokenAuthExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 用户 token 过期
     * @return
     */
    @ExceptionHandler(value = TokenAuthExpiredException.class)
    @ResponseBody
    public String tokenExpiredExceptionHandler(HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(401);
        log.warn("用户 token 过期");
        return "用户 token 过期";
    }
}
