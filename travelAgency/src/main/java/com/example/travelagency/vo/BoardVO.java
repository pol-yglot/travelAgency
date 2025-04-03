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

    public BoardVO() {
    }

    public BoardVO(int BOARD_ID, int USER_ID, String BOARD_TITLE, String BOARD_CONTENT, Date CREATED_AT, Date UPDATED_AT) {
        this.BOARD_ID = BOARD_ID;
        this.USER_ID = USER_ID;
        this.BOARD_TITLE = BOARD_TITLE;
        this.BOARD_CONTENT = BOARD_CONTENT;
        this.CREATED_AT = CREATED_AT;
        this.UPDATED_AT = UPDATED_AT;
    }
}
