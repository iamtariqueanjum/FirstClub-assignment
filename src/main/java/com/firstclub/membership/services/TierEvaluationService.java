package com.firstclub.membership.services;

import com.firstclub.membership.entity.TierCriteria;
import com.firstclub.membership.entity.User;
import com.firstclub.membership.enums.MembershipTier;
import com.firstclub.membership.repository.TierCriteriaRepository;
import com.firstclub.membership.services.strategy.TierEvaluationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TierEvaluationService {

    private final TierCriteriaRepository criteriaRepository;
    private final List<TierEvaluationStrategy> strategies;


    public MembershipTier evaluateQualifiedTier(User user) {
        List<MembershipTier> tiersHighToLow = List.of(
                MembershipTier.PLATINUM,
                MembershipTier.GOLD,
                MembershipTier.SILVER
        );

        for (MembershipTier tier : tiersHighToLow) {
            List<TierCriteria> criteriaList = criteriaRepository
                    .findByTierAndEnabledTrue(tier);

            boolean qualifies = criteriaList.stream()
                    .anyMatch(criteria -> strategies.stream()
                            .filter(s -> s.supports(criteria))
                            .anyMatch(s -> s.evaluate(user, criteria)));

            if (qualifies) {
                // Qualified tier
                return tier;
            }
        }
        // Default tier
        return MembershipTier.SILVER;
    }


}
