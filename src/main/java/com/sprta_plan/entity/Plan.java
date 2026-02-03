package com.sprta_plan.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="plans")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private String user;
    private String password;

    public Plan(String title, String content,String user, String password){
        this.title=title;
        this.content=content;
        this.user=user;
        this.password=password;

    }

    public void planUpdate(String title ,String user, String password){
        this.title=title;
        this.user=user;
        this.password=password;
    }
}
