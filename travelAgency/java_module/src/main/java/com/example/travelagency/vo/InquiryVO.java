package com.example.travelagency.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class InquiryVO {
    private int INQUIRY_ID;
    private int USER_ID;
    private String INQUIRY_CONTENT;
    private Date CREATED_AT;
    private Date UPDATED_AT;
    /* 기존 정보와 문의 정보 다를 수 있음 */
    private String USER_ACCOUNT;
    private String USER_NAME;
    private String USER_PHONE;
    private String USER_EMAIL;
    private String INQUIRY_TITLE;
    private String USER_TYPE;
    private String INQ_TYPE;

    @JsonProperty("START_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date START_DATE;

    @JsonProperty("START_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date END_DATE;
}
