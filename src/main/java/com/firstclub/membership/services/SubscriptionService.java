package com.firstclub.membership.services;

import com.firstclub.membership.entity.MembershipPlan;
import com.firstclub.membership.entity.User;
import com.firstclub.membership.entity.UserSubscription;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.repository.MembershipPlanRepository;
import com.firstclub.membership.repository.UserRepository;
import com.firstclub.membership.repository.UserSubscriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final UserSubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final MembershipPlanRepository planRepository;

    @Transactional
    public UserSubscription subscribe(Long userId, Long planId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipPlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        if (!plan.getActive()) {
            throw new RuntimeException("Plan is not active");
        }

        if (subscriptionRepository.existsByUserIdAndStatus(userId, SubscriptionStatus.ACTIVE)) {
            throw new RuntimeException("User already has an active subscription");
        }

        LocalDateTime now = LocalDateTime.now();
        UserSubscription subscription = new UserSubscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setCurrentTier(plan.getTier());
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setStartDate(now);
        subscription.setEndDate(now.plusMonths(plan.getDuration().getMonths()));
        return subscriptionRepository.save(subscription);
    }

    public UserSubscription getActiveSubscription(Long userId) {
        return subscriptionRepository
                .findByUserIdAndStatus(userId, SubscriptionStatus.ACTIVE)
                .orElse(null);
    }

}
