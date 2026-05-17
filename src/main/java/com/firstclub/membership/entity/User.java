package com.firstclub.membership.entity;

import com.firstclub.membership.enums.UserCohort;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monthlyOrderValue = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer totalOrderCount = 0;

    @Enumerated(EnumType.STRING)
    private UserCohort cohort =  UserCohort.REGULAR;

}
