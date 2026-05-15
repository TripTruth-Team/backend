package com.likelion.tripTruth.domain.plan.entity;

import com.likelion.tripTruth.domain.member.entity.GroupMember;
import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "vote",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_vote_group_member", columnNames = {"group_member_id"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_member_id", nullable = false)
    private GroupMember groupMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommended_plan_id", nullable = false)
    private RecommendedPlan recommendedPlan;

    @Builder
    public Vote(GroupMember groupMember, RecommendedPlan recommendedPlan) {
        this.groupMember = groupMember;
        this.recommendedPlan = recommendedPlan;
    }
}