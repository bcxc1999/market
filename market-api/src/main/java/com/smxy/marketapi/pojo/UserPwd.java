package com.smxy.marketapi.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_pwd")
public class UserPwd extends Model<UserPwd> implements Serializable {

    @TableId
    private int id;

    @JSONField(format = "yyyy-MM-dd HH-mm-ss")
    @TableField("modify")
    private Date modify;

    @TableField("password")
    private String pwd;

    @TableField("phone")
    private String phone;


}
