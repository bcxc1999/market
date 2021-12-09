package com.smxy.marketconsumer.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smxy.marketapi.bean.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TokenUtil {
    @Value("${token.privateKey}")
    private String privateKey;

    /**
     * 加密token.
     */
    public String getToken(String userPhone) {
        //这个是放到负载payLoad 里面,魔法值可以使用常量类进行封装.
        System.out.println(System.currentTimeMillis());
        String token = JWT
                .create()
                .withClaim("userPhone",userPhone)
                .withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(privateKey));
        return token;
    }

    /**
     * 解析token.
     * (优化可以用常量固定魔法值+使用DTO在 mvc 之前传输数据，而不是 map,这里因为篇幅原因就不做了)
     * {
     * "userId": "3412435312",
     * "userRole": "ROLE_USER",
     * "timeStamp": "134143214"
     * }
     */
    public TokenDTO parseToken(String token) {
        TokenDTO tokenDTO = new TokenDTO();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey))
                .build().verify(token);
        Claim userPhone = decodedjwt.getClaim("userPhone");
        Claim timeStamp = decodedjwt.getClaim("timeStamp");
        tokenDTO.setUserPhone(userPhone.asString());
        tokenDTO.setTimeStamp(timeStamp.asLong().toString());
        return tokenDTO;
    }
}

