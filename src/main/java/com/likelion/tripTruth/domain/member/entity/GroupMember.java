package com.likelion.tripTruth.domain.member.entity;

import com.likelion.tripTruth.domain.member.enums.MemberRole;
import com.likelion.tripTruth.domain.tripgroup.entity.TripGroup;
import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_group_id", nullable = false)
    private TripGroup tripGroup;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MemberRole role;

    @Column(name = "is_survey_completed", nullable = false)
    private boolean isSurveyCompleted;

    @Builder
    public GroupMember(TripGroup tripGroup, String nickname, MemberRole role, boolean isSurveyCompleted) {
        this.tripGroup = tripGroup;
        this.nickname = nickname;
        this.role = role;
        this.isSurveyCompleted = isSurveyCompleted;
    }
}