package com.firstclub.membership.scheduler;

import com.firstclub.membership.entity.User;
import com.firstclub.membership.entity.UserSubscription;
import com.firstclub.membership.enums.MembershipTier;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.repository.UserRepository;
import com.firstclub.membership.repository.UserSubscriptionRepository;
import com.firstclub.membership.services.TierEvaluationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TierEvaluationScheduler {

    private final UserRepository userRepository;
    private final UserSubscriptionRepository subscriptionRepository;
    private final TierEvaluationService tierEvaluationService;

    /*
     * Runs every 10 seconds.
     * For each user with an active subscription, evaluates their
     * qualified tier and auto-promotes or demotes if it has changed.
     */
    @Scheduled(cron = "${membership.tier.evaluation.cron:0/10 * * * * *}")
    @Transactional
    public void evaluateAllUserTiers() {
        log.info("Starting tier evaluation job...");
        List<User> allUsers = userRepository.findAll();
        int promoted = 0, demoted = 0, unchanged = 0;

        for (User user : allUsers) {
            try {
                Optional<UserSubscription> subscriptions = subscriptionRepository
                        .findByUserIdAndStatus(user.getId(), SubscriptionStatus.ACTIVE);

                if (subscriptions.isEmpty()) continue;

                UserSubscription sub = subscriptions.get();
                MembershipTier currentTier = sub.getCurrentTier();
                MembershipTier qualifiedTier = tierEvaluationService.evaluateQualifiedTier(user);

                if (qualifiedTier == currentTier) {
                    unchanged++;
                    continue;
                }

                boolean isPromotion = qualifiedTier.getLevel() > currentTier.getLevel();
                sub.setCurrentTier(qualifiedTier);
                sub.setTierChangeReason(
                        (isPromotion ? "Auto-promoted" : "Auto-demoted") +
                                " from " + currentTier.getDisplayName() +
                                " to "   + qualifiedTier.getDisplayName()
                );
                subscriptionRepository.save(sub);

                if (isPromotion) promoted++; else demoted++;

                log.info("User {} {} from {} to {}",
                        user.getId(),
                        isPromotion ? "promoted" : "demoted",
                        currentTier,
                        qualifiedTier
                );

            } catch (Exception e) {
                log.error("Tier evaluation failed for user {}: {}", user.getId(), e.getMessage());
            }
        }
        log.info("Tier evaluation complete — promoted: {}, demoted: {}, unchanged: {}", promoted, demoted, unchanged);
    }
}
