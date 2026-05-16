package com.firstclub.membership.entity;

import com.firstclub.membership.enums.MembershipTier;
import com.firstclub.membership.enums.PlanDuration;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "membership_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    private PlanDuration duration;

    @Enumerated(EnumType.STRING)
    private MembershipTier tier;

}
