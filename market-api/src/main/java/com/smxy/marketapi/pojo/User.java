package com.smxy.marketapi.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
@TableName("t_user_info")
public class User extends Model<User> implements Serializable {
    @TableId("id")
    private int id;

    @TableField("modify")
    private Date modify;

    @TableField("userName")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("realName")
    private String rName;

    @TableField("clazz")
    private String clazz;

    @TableField("sno")
    private String sno;

    @TableField("dormitory")
    private String dormitory;

    @TableField("dormitoryNo")
    private String dormitoryNo;

    @TableField("sex")
    private int sex;

    @TableField("createTime")
    @JSONField(format = "yyyy-MM-dd HH-mm-ss")
    private Date createTime;

    @TableField("avatar")
    private String avatar;
}
