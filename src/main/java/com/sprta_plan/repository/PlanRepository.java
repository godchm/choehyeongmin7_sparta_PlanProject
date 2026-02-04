package com.sprta_plan.repository;

import com.sprta_plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Long> {
    // service로 plan DB를 가져다 주기전에 수정일은 내림차순으로 정렬해준다. JPA 쿼리 메서드
    List<Plan> findAllByOrderByModifiedAtDesc();
}
