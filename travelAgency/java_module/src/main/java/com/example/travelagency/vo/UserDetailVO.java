package com.example.travelagency.vo;

import lombok.Data;
import org.w3c.dom.Text;

import java.util.Date;

@Data
public class UserDetailVO {
    private int USER_DETAIL_ID;
    private int USER_ID;
    private String USER_ADDRESS;
    private String USER_PROFILE_IMAGE_URL;
    private String USER_PREFERENCE;
    private Date CREATED_AT;
    private Date UPDATED_AT;
}
