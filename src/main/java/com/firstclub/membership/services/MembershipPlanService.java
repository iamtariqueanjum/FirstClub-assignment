package com.firstclub.membership.services;

import com.firstclub.membership.entity.MembershipPlan;
import com.firstclub.membership.repository.MembershipPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipPlanService {

    private final MembershipPlanRepository planRepository;

    public List<MembershipPlan> getAllActivePlans() {
        return planRepository.findByActiveTrue();
    }


}
