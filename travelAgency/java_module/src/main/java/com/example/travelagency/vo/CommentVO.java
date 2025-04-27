package com.example.travelagency.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private int COMMENT_ID;
    private int BOARD_ID;
    private int COMMENT_WRITER;
    private String COMMENT_CONTENT;
    private Date CREATED_AT;
    private Date UPDATED_AT;
}
