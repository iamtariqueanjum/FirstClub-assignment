package com.firstclub.membership.services.strategy;

import com.firstclub.membership.entity.TierCriteria;
import com.firstclub.membership.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MonthlyOrderValueStrategy implements TierEvaluationStrategy {

    @Override
    public boolean supports(TierCriteria criteria) {
        return criteria.getMinMonthlyOrderValue() != null;
    }

    @Override
    public boolean evaluate(User user, TierCriteria criteria) {
        return user.getMonthlyOrderValue()
                .compareTo(criteria.getMinMonthlyOrderValue()) >= 0;
    }

}
