package com.smxy.marketapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    private String name;

    private String phone;

    private String rName;

    private String clazz;

    private String sno;

    private String dormitory;

    private String dormitoryNo;

    private int sex;

    private String avatar;
}
