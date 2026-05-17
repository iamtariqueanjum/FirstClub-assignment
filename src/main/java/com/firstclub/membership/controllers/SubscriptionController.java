package com.firstclub.membership.controllers;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.entity.UserSubscription;
import com.firstclub.membership.enums.MembershipTier;
import com.firstclub.membership.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<UserSubscription> subscribe(@RequestBody SubscribeRequest request) {
        UserSubscription sub = subscriptionService.subscribe(
                request.getUserId(),
                request.getPlanId()
        );
        return ResponseEntity.ok(sub);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserSubscription> getUserSubscription(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getActiveSubscription(userId));
    }


    @PatchMapping("/user/{userId}/tier")
    public ResponseEntity<UserSubscription> changeTier(
            @PathVariable Long userId,
            @RequestParam MembershipTier newTier) {
        return ResponseEntity.ok(subscriptionService.changeTier(userId, newTier));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<UserSubscription> cancel(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.cancel(userId));
    }

}
