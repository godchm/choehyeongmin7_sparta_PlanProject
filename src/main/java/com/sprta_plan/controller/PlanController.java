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


    // 생성
    @PostMapping("/plans")
    public ResponseEntity<CreatePlanResponse> creatPlan(@RequestBody CreatePlanRequest request){
        CreatePlanResponse result=planService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PostMapping("/plans/{planId}/comments")
    public ResponseEntity<CreateCommentResponse> creatComment(
            @RequestBody CreateCommentRequest request,
            @PathVariable Long planId
    ){
        CreateCommentResponse result=commentService.commentsave(request,planId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    // 단건 조회
    @GetMapping("/plans/{planId}")
    public ResponseEntity<GetOnePlanResponse> getOneUser(@PathVariable Long planId) {
        GetOnePlanResponse result = planService.getOne(planId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 모두 조회
    @GetMapping("/plans")
    public ResponseEntity<List<GetOnePlanResponse>>getPlans(){
        List<GetOnePlanResponse> result=planService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // 수정
    @PutMapping("/plans/{planId}")
    public ResponseEntity<UpdatePlanResponse> update(
            @PathVariable Long planId,
            @RequestBody UpdatePlanRequest request
    ){
        UpdatePlanResponse result=planService.update(planId,request);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    // 삭제 기능
    @DeleteMapping("/plans/{planId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long planId,
            @RequestBody DeletePlanRequest request
    ){
        planService.delete(planId,request.getPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
