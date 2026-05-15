package com.likelion.tripTruth.domain.survey.entity;

import com.likelion.tripTruth.domain.survey.enums.ActivityType;
import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_activity")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyActivity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_response_id", nullable = false)
    private SurveyResponse surveyResponse;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false, length = 50)
    private ActivityType activityType;

    @Builder
    public SurveyActivity(SurveyResponse surveyResponse, ActivityType activityType) {
        this.surveyResponse = surveyResponse;
        this.activityType = activityType;
    }
}