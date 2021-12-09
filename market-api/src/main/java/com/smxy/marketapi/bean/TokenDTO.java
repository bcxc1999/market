package com.smxy.marketapi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO implements Serializable {

    private String userPhone;
    private String timeStamp;
}
