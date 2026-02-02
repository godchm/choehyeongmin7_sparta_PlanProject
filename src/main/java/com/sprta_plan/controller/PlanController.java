package com.sprta_plan.controller;


import com.sprta_plan.dto.CreatePlanRequest;
import com.sprta_plan.dto.CreatePlanResponse;
import com.sprta_plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;


    @PostMapping("/plans")
    public ResponseEntity<CreatePlanResponse> creatPlan(@RequestBody CreatePlanRequest request){
        CreatePlanResponse result=planService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }
}
