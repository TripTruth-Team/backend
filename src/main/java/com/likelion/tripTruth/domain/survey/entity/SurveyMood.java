package com.likelion.tripTruth.domain.survey.entity;

import com.likelion.tripTruth.domain.survey.enums.MoodType;
import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_mood")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyMood extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_response_id", nullable = false)
    private SurveyResponse surveyResponse;

    @Enumerated(EnumType.STRING)
    @Column(name = "mood_type", nullable = false, length = 50)
    private MoodType moodType;

    @Builder
    public SurveyMood(SurveyResponse surveyResponse, MoodType moodType) {
        this.surveyResponse = surveyResponse;
        this.moodType = moodType;
    }
}