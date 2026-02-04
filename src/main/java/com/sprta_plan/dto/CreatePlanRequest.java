package com.sprta_plan.dto;

import lombok.Getter;

@Getter
public class CreatePlanRequest {

    private String title;
    private String content;
    private String user;
    private String password;

}
