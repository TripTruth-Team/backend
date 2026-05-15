package com.likelion.tripTruth.domain.plan.entity;

import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "daily_schedule")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailySchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommended_plan_id", nullable = false)
    private RecommendedPlan recommendedPlan;

    @Column(name = "day_number", nullable = false)
    private Integer dayNumber;

    @Column(name = "start_time", nullable = false, length = 10)
    private String startTime;

    @Column(name = "activity_title", nullable = false, length = 100)
    private String activityTitle;

    @Column(name = "satisfied_need", length = 100)
    private String satisfiedNeed;

    @Builder
    public DailySchedule(RecommendedPlan recommendedPlan, Integer dayNumber, String startTime, String activityTitle, String satisfiedNeed) {
        this.recommendedPlan = recommendedPlan;
        this.dayNumber = dayNumber;
        this.startTime = startTime;
        this.activityTitle = activityTitle;
        this.satisfiedNeed = satisfiedNeed;
    }
}