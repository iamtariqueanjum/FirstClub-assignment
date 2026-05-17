package com.firstclub.membership.services.strategy;

import com.firstclub.membership.entity.TierCriteria;
import com.firstclub.membership.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CohortStrategy implements TierEvaluationStrategy {

    @Override
    public boolean supports(TierCriteria criteria) {
        return criteria.getCohort() != null;
    }

    @Override
    public boolean evaluate(User user, TierCriteria criteria) {
        return user.getCohort() == criteria.getCohort();
    }
    
}
