package com.sprta_plan.service;


import com.sprta_plan.dto.CreatePlanRequest;
import com.sprta_plan.dto.CreatePlanResponse;
import com.sprta_plan.dto.GetOnePlanResponse;
import com.sprta_plan.entity.Plan;
import com.sprta_plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    public GetOnePlanResponse getOne(Long userId){
        Plan plan=planRepository.findById(userId).orElseThrow(
                ()-> new IllegalStateException("없는 유저 입니다.")
        );

        return new GetOnePlanResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getContent(),
                plan.getUser(),
                plan.getCreatedAt(),
                plan.getModifiedAt()
        );

    }

    @Transactional(readOnly = true)
    public List<GetOnePlanResponse> getAll() {
        List<Plan> plans=planRepository.findAllByOrderByModifiedAtDesc();
        List<GetOnePlanResponse> dtos=new ArrayList<>();
        for (Plan plan : plans) {
            GetOnePlanResponse dto= new GetOnePlanResponse(
                    plan.getId(),
                    plan.getTitle(),
                    plan.getContent(),
                    plan.getUser(),
                    plan.getCreatedAt(),
                    plan.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;

    }
}
