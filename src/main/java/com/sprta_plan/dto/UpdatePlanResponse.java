package com.sprta_plan.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePlanResponse {


    private final Long id;
    private final String title;
    private final String content;
    private final String user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public UpdatePlanResponse( Long id,String title, String content,String user,LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.id=id;
        this.title = title;
        this.content=content;
        this.user = user;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;


    }
}
