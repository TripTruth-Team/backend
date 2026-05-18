package com.likelion.tripTruth.domain.survey.entity;

import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_avoidance_tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyAvoidanceTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_response_id", nullable = false)
    private SurveyResponse surveyResponse;

    @Column(name = "tag_name", nullable = false, length = 50)
    private String tagName;

    @Builder
    public SurveyAvoidanceTag(SurveyResponse surveyResponse, String tagName) {
        this.surveyResponse = surveyResponse;
        this.tagName = tagName;
    }
}