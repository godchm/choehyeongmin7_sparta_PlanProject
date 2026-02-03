package com.sprta_plan.service;


import com.sprta_plan.dto.*;
import com.sprta_plan.entity.Comment;
import com.sprta_plan.entity.Plan;
import com.sprta_plan.repository.CommentRepository;
import com.sprta_plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PlanService planService;

    // 새 정보 입력
    @Transactional
    public CreateCommentResponse commentsave(CreateCommentRequest request,Long planId) {

        long count=commentRepository.countByPlanId(planId);
        if(count>=10){
            throw new IllegalArgumentException("댓글은 10개까지만 가능합니다.");
        }

        Comment comment= new Comment(
                planId,
                request.getUser(),
                request.getContent(),
                request.getPassword()

        );
        Comment savedComment=commentRepository.save(comment);

        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getUser(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

}
