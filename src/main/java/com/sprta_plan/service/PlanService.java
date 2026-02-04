package com.sprta_plan.service;


import com.sprta_plan.dto.*;
import com.sprta_plan.entity.Comment;
import com.sprta_plan.entity.Plan;
import com.sprta_plan.repository.CommentRepository;
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
    private final CommentRepository commentRepository;


    // 새 일정 입력
    @Transactional
    public CreatePlanResponse save(CreatePlanRequest request) {

        requestPlanTest(request);

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


    // 독립적인 관계이므로 if/else문이 아니라 if문으로 쓴다.
     private void requestPlanTest(CreatePlanRequest request){

        if (request.getTitle()==null||request.getTitle().trim().isEmpty() || request.getTitle().length()>30){
            throw new IllegalArgumentException("일정 제목을 입력하거나, 일정 제목을 30자 이내로 입력하세요.");
        }
         if (request.getContent()==null||request.getContent().trim().isEmpty() || request.getContent().length()>200){
             throw new IllegalArgumentException("일정 제목을 입력하거나, 일정 제목을 200자 이내로 입력하세요.");
         }
         // " " || null
         if (request.getUser()==null||request.getUser().trim().isEmpty()){
             throw new IllegalArgumentException("일정 사용자명은 필수로 입력하세요.");
         }
         if (request.getContent()==null||request.getPassword().trim().isEmpty()){
             throw new IllegalArgumentException("일정 비밀번호는 필수로 입력하세요");
         }

     }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetOnePlanResponse getOne(Long userId){
        // 일정 한건 조회
        Plan plan=planRepository.findById(userId).orElseThrow(
                ()-> new IllegalStateException("없는 유저 입니다.")
        );
        // 해당 일정에 대한 모든 댓글 조회
        List<Comment> commentslist = commentRepository.findByPlanId(plan.getId());
        return new GetOnePlanResponse(
                   plan,
                commentslist
        );

    }

    // 모두 조회
    @Transactional(readOnly = true)
    public List<GetOnePlanResponse> getAll() {
        List<Plan> plans=planRepository.findAllByOrderByModifiedAtDesc();
        List<GetOnePlanResponse> dtos=new ArrayList<>();
        for (Plan plan : plans) {
            GetOnePlanResponse dto= new GetOnePlanResponse(
                   plan,
                    null);
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

