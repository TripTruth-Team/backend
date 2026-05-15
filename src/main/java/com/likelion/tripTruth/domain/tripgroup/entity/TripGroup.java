package com.likelion.tripTruth.domain.tripgroup.entity;

import com.likelion.tripTruth.domain.conflict.entity.ConflictCard;
import com.likelion.tripTruth.domain.member.entity.GroupMember;
import com.likelion.tripTruth.domain.plan.entity.RecommendedPlan;
import com.likelion.tripTruth.domain.tripgroup.enums.GroupStatus;
import com.likelion.tripTruth.domain.tripgroup.enums.TripLength;
import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TripGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_length", nullable = false, length = 50)
    private TripLength tripLength;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "invite_code", nullable = false, unique = true)
    private String inviteCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private GroupStatus status;


    @OneToMany(mappedBy = "tripGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "tripGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConflictCard> conflictCards = new ArrayList<>();

    @OneToMany(mappedBy = "tripGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecommendedPlan> recommendedPlans = new ArrayList<>();

    @Builder
    public TripGroup(String name, TripLength tripLength, LocalDate startDate, LocalDate endDate, String inviteCode, GroupStatus status) {
        this.name = name;
        this.tripLength = tripLength;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inviteCode = inviteCode;
        this.status = status;
    }


    /**
     * 그룹의 상태를 변경
     */
    public void updateStatus(GroupStatus newStatus) {
        this.status = newStatus;
    }
}