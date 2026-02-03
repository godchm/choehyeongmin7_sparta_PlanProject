package com.sprta_plan.controller;


import com.sprta_plan.dto.CreatePlanRequest;
import com.sprta_plan.dto.CreatePlanResponse;
import com.sprta_plan.dto.GetOnePlanResponse;
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


    @PostMapping("/plans")
    public ResponseEntity<CreatePlanResponse> creatPlan(@RequestBody CreatePlanRequest request){
        CreatePlanResponse result=planService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @GetMapping("/plans/{planId}")
    public ResponseEntity<GetOnePlanResponse> getOneUser(@PathVariable Long planId) {
        GetOnePlanResponse result = planService.getOne(planId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<GetOnePlanResponse>>getPlans(){
        List<GetOnePlanResponse> result=planService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
