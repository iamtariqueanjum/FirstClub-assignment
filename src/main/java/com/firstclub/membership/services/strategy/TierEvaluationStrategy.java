package com.firstclub.membership.services.strategy;

import com.firstclub.membership.entity.TierCriteria;
import com.firstclub.membership.entity.User;

public interface TierEvaluationStrategy {
    boolean supports(TierCriteria criteria);
    boolean evaluate(User user, TierCriteria criteria);
}
