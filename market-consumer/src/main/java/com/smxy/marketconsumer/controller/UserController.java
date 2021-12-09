package com.smxy.marketconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.smxy.marketapi.bean.UserPwdDTO;
import com.smxy.marketapi.bean.UserVO;
import com.smxy.marketapi.service.UserPwdService;
import com.smxy.marketapi.service.UserService;
import com.smxy.marketconsumer.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    private final static int TIME_OUT = 1000 * 60 * 5;

    @Reference
    UserPwdService userPwdService;

    @Resource
    CacheService cacheService;

    @Reference
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/password/recover")
    @ResponseBody
    public String pwdRecover(@RequestParam("phone") String phone,
                             @RequestParam("newPassword") String pwd,
                             @RequestParam("verificationCode") String code){
        if(!isExist(phone)){
            return "该手机尚未注册";
        }
        if(code.equals(cacheService.get(phone))){
            delCode(phone);
            userPwdService.updateByPhone(UserPwdDTO.builder()
                    .phone(phone)
                    .pwd(passwordEncoder.encode(pwd)).build());
            return "修改成功";

        }
        return "验证码错误";
    }

    @RequestMapping("/completeInfo")
    public String completeInfo(@RequestParam(value = "realName",required = false) String rName,
                               @RequestParam String phone,
                               @RequestParam(required = false) String clazz,
                               @RequestParam(required = false) String dormitory,
                               @RequestParam(required = false) String dormitoryNo,
                               @RequestParam(required = false) String sex,
                               @RequestParam(value = "userName",required = false) String name,
                               @RequestParam(required = false) String avatar){

        return userService.updateByPhone(UserVO.builder()
                .phone(phone)
                .rName(rName)
                .clazz(clazz)
                .name(name)
                .dormitory(dormitory)
                .dormitoryNo(dormitoryNo)
                .avatar(avatar)
                .sex(Integer.parseInt(sex)).build())+"";

    }

    /**
     * 是否存在该手机用户
     * @param phone
     * @return
     */
    public boolean isExist(String phone){
        return userService.queryByPhone(phone) != null;
    }

    /**
     * 使用完验证码 做删除处理 避免一个验证码多次操作
     * @param phone
     */
    private void delCode(String phone){
        if(cacheService.hasKey(phone)){
            cacheService.delete(phone);
        }
    }

}
