package com.likelion.tripTruth.domain.plan.entity;

import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plan_tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommended_plan_id", nullable = false)
    private RecommendedPlan recommendedPlan;

    @Column(name = "tag_name", nullable = false, length = 50)
    private String tagName;

    @Builder
    public PlanTag(RecommendedPlan recommendedPlan, String tagName) {
        this.recommendedPlan = recommendedPlan;
        this.tagName = tagName;
    }
}