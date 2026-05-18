package com.likelion.tripTruth.domain.survey.entity;

import com.likelion.tripTruth.domain.member.entity.GroupMember;
import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey_response")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResponse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_member_id", nullable = false, unique = true)
    private GroupMember groupMember;

    @Column(name = "avoidance_text", length = 200)
    private String avoidanceText;

    @Column(nullable = false)
    private Integer budget;

    @Column(length = 200)
    private String comment;

    @OneToMany(mappedBy = "surveyResponse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyMood> surveyMoods = new ArrayList<>();

    @OneToMany(mappedBy = "surveyResponse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyActivity> surveyActivities = new ArrayList<>();

    @OneToMany(mappedBy = "surveyResponse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyAvoidanceTag> surveyAvoidanceTags = new ArrayList<>();

    @Builder
    public SurveyResponse(GroupMember groupMember, String avoidanceText, Integer budget, String comment) {
        this.groupMember = groupMember;
        this.avoidanceText = avoidanceText;
        this.budget = budget;
        this.comment = comment;
    }
}