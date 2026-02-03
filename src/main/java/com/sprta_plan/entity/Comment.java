package com.sprta_plan.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String user;
    private String content;
    private String password;
    private Long planId;

    public Comment(Long planId,String user, String content, String password){
        this.planId = planId;
        this.user=user;
        this.content=content;
        this.password=password;

    }
}
