package com.sprta_plan.service;


import com.sprta_plan.dto.CreatePlanRequest;
import com.sprta_plan.dto.CreatePlanResponse;
import com.sprta_plan.dto.GetOnePlanResponse;
import com.sprta_plan.entity.Plan;
import com.sprta_plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    @Transactional
    public CreatePlanResponse save(CreatePlanRequest request) {
        Plan plan= new Plan(
                request.getTitle(),
                request.getContent(),
                request.getUser(),
                request.getPassword()
        );
        Plan savedUser=planRepository.save(plan);

        return new CreatePlanResponse(
                savedUser.getTitle(),
                savedUser.getContent(),
                savedUser.getUser(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }






}
