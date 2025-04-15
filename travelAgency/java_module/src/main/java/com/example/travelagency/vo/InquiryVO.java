package com.example.travelagency.vo;

import lombok.Data;

import java.util.Date;

@Data
public class InquiryVO {
    private int INQUIRY_ID;
    private int USER_ID;
    private int PRODUCT_ID;
    private String INQUIRY_TITLE;
    private String INQUIRY_CONTENT;
    private Date CREATED_AT;
    private Date UPDATED_AT;
    /* 기존 정보와 문의 정보 다를 수 있음 */
    private String USER_ACCOUNT;
    private String USER_EMAIL;
    private String USER_NAME;
    private String USER_PHONE;
}
