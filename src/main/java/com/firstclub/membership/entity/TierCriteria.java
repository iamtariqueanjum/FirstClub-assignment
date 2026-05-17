package com.firstclub.membership.entity;


import com.firstclub.membership.enums.MembershipTier;
import com.firstclub.membership.enums.UserCohort;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tier_criteria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TierCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MembershipTier tier;

    private Integer minOrderCount;

    @Column
    private BigDecimal minMonthlyOrderValue;

    @Enumerated(EnumType.STRING)
    private UserCohort cohort;

    private Boolean enabled = true;

}
