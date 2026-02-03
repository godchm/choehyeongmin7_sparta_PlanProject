package com.sprta_plan.service;


import com.sprta_plan.dto.*;
import com.sprta_plan.entity.Plan;
import com.sprta_plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;


    // 새 정보 입력
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

    // 단건 조회
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

    // 모두 조회
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


    // 수정 기능
    @Transactional
    public UpdatePlanResponse update(Long planId, UpdatePlanRequest request) {
        Plan plan=planRepository.findById(planId).orElseThrow(
                ()-> new IllegalStateException("없는 유저 입니다.")
        );
        if(!plan.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        plan.planUpdate(
                request.getTitle(),
                request.getUser(),
                request.getPassword()
        );

        return new UpdatePlanResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getContent(),
                plan.getUser(),
                plan.getCreatedAt(),
                plan.getModifiedAt()
        );
    }

    // 삭제 기능
    @Transactional
    public void delete(Long planId, String password){
        boolean existence= planRepository.existsById(planId);

        // 유저가 없는 경우
        if(!existence){
            throw new IllegalArgumentException("없는 유저 입니다.");
        }

        Plan plan=planRepository.findById(planId)
                .orElseThrow();

        if(!plan.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

            planRepository.deleteById(planId);
        }



    }

