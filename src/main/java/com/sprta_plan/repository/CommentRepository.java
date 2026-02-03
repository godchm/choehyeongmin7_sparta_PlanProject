package com.sprta_plan.repository;

import com.sprta_plan.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  CommentRepository  extends JpaRepository<Comment,Long> {
    // JPA 쿼리 메서드
    List<Comment> findByPlanId(Long planId);
    long countByPlanId(Long planId);
}
