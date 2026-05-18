package com.likelion.tripTruth.domain.tripgroup.repository;

import com.likelion.tripTruth.domain.tripgroup.entity.TripGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripGroupRepository extends JpaRepository<TripGroup, Long> {
    boolean existsByInviteCode(String inviteCode);

    // 초대 코드로 여행 그룹 조회
    Optional<TripGroup> findByInviteCode(String inviteCode);
}