package com.firstclub.membership.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum MembershipTier {
    SILVER(1, "Silver"),
    GOLD(2, "Gold"),
    PLATINUM(3, "Platinum");

    private final int level;
    private final String displayName;

}
