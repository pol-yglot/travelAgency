package com.example.travelagency.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {
    private int BOARD_ID;
    private int USER_ID;
    private String BOARD_TITLE;
    private String BOARD_CONTENT;
    private Date CREATED_AT;
    private Date UPDATED_AT;
    private int BOARD_HITS;
}
