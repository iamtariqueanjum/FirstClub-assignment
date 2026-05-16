package com.firstclub.membership.repository;

import com.firstclub.membership.entity.Usersubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<Usersubscription, Long> {

}
