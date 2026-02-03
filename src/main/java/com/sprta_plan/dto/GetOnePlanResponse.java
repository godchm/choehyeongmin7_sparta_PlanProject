package com.sprta_plan.dto;


import com.sprta_plan.entity.Comment;
import com.sprta_plan.entity.Plan;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter

public class GetOnePlanResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<Comment> comments;


    public GetOnePlanResponse(Plan plan, List<Comment> comments){
        this.id= plan.getId();
        this.title=plan.getTitle();
        this.content= plan.getContent();
        this.user=plan.getUser();
        this.createdAt=plan.getCreatedAt();
        this.modifiedAt=plan.getModifiedAt();
        this.comments=comments;
    }



}
