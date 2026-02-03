package com.sprta_plan.dto;


import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String user;
    private String content;
    private String password;
}

