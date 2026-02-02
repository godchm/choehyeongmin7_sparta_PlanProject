package com.sprta_plan.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePlanResponse {


    private final String title;
    private final String content;
    private final String user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public CreatePlanResponse( String title,String content ,String user,LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.title = title;
        this.content=content;
        this.user = user;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }
}
