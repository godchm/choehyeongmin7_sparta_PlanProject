package com.sprta_plan.repository;

import com.sprta_plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Long> {
    List<Plan> findAllByOrderByModifiedAtDesc();
}
