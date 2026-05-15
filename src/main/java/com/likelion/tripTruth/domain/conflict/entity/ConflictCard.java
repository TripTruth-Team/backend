package com.likelion.tripTruth.domain.conflict.entity;

import com.likelion.tripTruth.domain.conflict.enums.ConflictType;
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
@Table(name = "conflict_card")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConflictCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_group_id", nullable = false)
    private TripGroup tripGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "conflict_type", nullable = false, length = 50)
    private ConflictType conflictType;

    @Column(nullable = false, length = 100)
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "conflictCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConflictDetail> conflictDetails = new ArrayList<>();

    @Builder
    public ConflictCard(TripGroup tripGroup, ConflictType conflictType, String title, String description) {
        this.tripGroup = tripGroup;
        this.conflictType = conflictType;
        this.title = title;
        this.description = description;
    }
}