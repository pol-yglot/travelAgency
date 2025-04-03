package com.example.travelagency.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    private int USER_ID;
    private String USER_ACCOUNT;
    private String USER_PASSWORD;
    private String USER_NAME;
    private String USER_PHONE;
    private Date CREATED_AT;
    private Date UPDATED_AT;
}
