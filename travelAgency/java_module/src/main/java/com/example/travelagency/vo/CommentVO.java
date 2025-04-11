package com.example.travelagency.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private int COMMENT_ID;
    private int BOARD_ID;
    private String COMMENT_WRITER;
    private String COMMENT_CONTENT;
    private Date COMMENT_REGDATE;
    private Date COMMENT_UPDATDATE;
    private String USER_ACCOUNT;
}
