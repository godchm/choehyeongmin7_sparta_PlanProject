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

        requestCommentTest(request);

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


    private void requestCommentTest(CreateCommentRequest request){

        if (request.getContent()==null||request.getContent().trim().isEmpty() || request.getContent().length()>100){
            throw new IllegalArgumentException("댓글 내용을 입력하거나, 댓글 내용을 100자 이내로 입력하세요.");
        }
        // " " || null
        if (request.getUser()==null||request.getUser().trim().isEmpty()){
            throw new IllegalArgumentException("댓글 사용자명은 필수로 입력하세요.");
        }
        if (request.getPassword()==null||request.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException("댓글 비밀번호는 필수로 입력하세요");
        }

    }


}
