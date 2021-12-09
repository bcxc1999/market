package com.smxy.marketconsumer.filter;

import com.smxy.marketapi.bean.TokenDTO;
import com.smxy.marketconsumer.exception.TokenAuthExpiredException;
import com.smxy.marketconsumer.service.CacheService;
import com.smxy.marketconsumer.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtil tokenUtil;
    @Resource
    CacheService cacheService;
    @Value("${token.privateKey}")
    private String privateKey;
    @Value("${token.yangToken}")
    private Long yangToken;
    @Value("${token.oldToken}")
    private Long oldToken;
    /**
     * 权限认证的拦截操作.
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("=======进入拦截器========");
        // 如果不是映射到方法直接通过,可以访问资源.
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        //为空就返回错误
        String token = httpServletRequest.getHeader("token");
        if (null == token || "".equals(token.trim())) {
            httpServletResponse.setStatus(401);
            return false;
        }
        log.info("==============token:" + token);
        TokenDTO tokenDTO = tokenUtil.parseToken(token);
        String userPhone = tokenDTO.getUserPhone();
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(tokenDTO.getTimeStamp());
        //1.判断 token 是否过期
        //年轻 token
        if (timeOfUse < yangToken) {
            log.info("年轻 token");
        }
        //老年 token 就刷新 token
        else if (timeOfUse >= yangToken && timeOfUse < oldToken) {
            httpServletResponse.setHeader("token",tokenUtil.getToken(userPhone));
        }
        //过期 token 就返回 token 无效.
        else {
            throw new TokenAuthExpiredException();
        }
        return true;
    }

}
