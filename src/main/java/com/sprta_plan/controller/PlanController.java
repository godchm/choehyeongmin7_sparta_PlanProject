package com.sprta_plan.controller;


import com.sprta_plan.dto.*;
import com.sprta_plan.service.CommentService;
import com.sprta_plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;
    private final CommentService commentService;


    // 생성.. 에러처리는 튜터님 도움 받음
    @PostMapping("/plans")
    public ResponseEntity<?> createPlan(@RequestBody CreatePlanRequest request) {

        CreatePlanResponse result = null;
        try {
            result = planService.save(request);
        } catch (Exception e) {
            // 데이터를 잘못보냈다. FORBIDDEN은 접근하지마 이런 느낌.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }


    // 댓글 생성... 에러처리는 튜터님 도움 받음
    @PostMapping("/plans/{planId}/comments")
    public ResponseEntity<?> createComment(
            @RequestBody CreateCommentRequest request,
            @PathVariable Long planId) {
        CreateCommentResponse result = null;

        try {
            result = commentService.commentsave(request, planId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    // 단건 조회
    @GetMapping("/plans/{planId}")
    public ResponseEntity<?> getOneUser(@PathVariable Long planId) {
        GetOnePlanResponse result=null;
        try {
             result= planService.getOne(planId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 모두 조회
    @GetMapping("/plans")
    public ResponseEntity<List<GetOnePlanResponse>> getPlans() {
        List<GetOnePlanResponse> result = planService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // 수정
    @PutMapping("/plans/{planId}")
    public ResponseEntity<?> update(
            @PathVariable Long planId,
            @RequestBody UpdatePlanRequest request
    ) {
        UpdatePlanResponse result=null;
        try {
            result = planService.update(planId, request);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    // 삭제 기능
    @DeleteMapping("/plans/{planId}")
    public ResponseEntity<?> delete(
            @PathVariable Long planId,
            @RequestBody DeletePlanRequest request
    ) {
        try {
            planService.delete(planId, request.getPassword());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
