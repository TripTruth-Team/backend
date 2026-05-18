package com.likelion.tripTruth.domain.tripgroup.repository;

import com.likelion.tripTruth.domain.tripgroup.entity.TripGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripGroupRepository extends JpaRepository<TripGroup, Long> {
    boolean existsByInviteCode(String inviteCode);
}