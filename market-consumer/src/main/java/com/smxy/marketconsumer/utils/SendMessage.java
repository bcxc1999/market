package com.smxy.marketconsumer.utils;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;

@Component
public class SendMessage {

    public  void send(String phone,String verificationCode) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        //在头文件中设置转码
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
        NameValuePair[] data = {
                new NameValuePair("Uid", "xiaolei@"),
                new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob", phone),
                new NameValuePair("smsText", "注册验证码是："+verificationCode+"。转给他人将导致账号被盗和个人信息泄漏，谨防受骗。")
        };
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getRequestHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result);
        post.releaseConnection();


    }

    public String randomVerificationCode(){
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for(int i=0;i<4;i++)
            code.append(random.nextInt(10));
        return code.toString();
    }
}