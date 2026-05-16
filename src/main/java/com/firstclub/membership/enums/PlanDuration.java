package com.firstclub.membership.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum PlanDuration {

    MONTHLY(1, "Monthly"),
    QUARTERLY(3, "Quarterly"),
    YEARLY(12, "Yearly");

    private final int months;
    private final String displayName;

}
