package com.likelion.tripTruth.domain.plan.entity;

import com.likelion.tripTruth.domain.tripgroup.entity.TripGroup;
import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recommended_plan")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendedPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_group_id", nullable = false)
    private TripGroup tripGroup;

    @Column(name = "plan_rank", nullable = false)
    private Integer planRank;

    @Column(nullable = false, length = 100)
    private String destination;

    @Column(name = "budget_status", nullable = false, length = 100)
    private String budgetStatus;

    @Column(name = "satisfaction_percent", nullable = false)
    private Integer satisfactionPercent;

    @Column(name = "is_decided", nullable = false)
    private boolean isDecided;

    @OneToMany(mappedBy = "recommendedPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanTag> planTags = new ArrayList<>();

    @OneToMany(mappedBy = "recommendedPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailySchedule> dailySchedules = new ArrayList<>();

    @Builder
    public RecommendedPlan(TripGroup tripGroup, Integer planRank, String destination, String budgetStatus, Integer satisfactionPercent, boolean isDecided) {
        this.tripGroup = tripGroup;
        this.planRank = planRank;
        this.destination = destination;
        this.budgetStatus = budgetStatus;
        this.satisfactionPercent = satisfactionPercent;
        this.isDecided = isDecided;
    }
}