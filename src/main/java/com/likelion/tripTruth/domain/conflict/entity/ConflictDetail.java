package com.likelion.tripTruth.domain.conflict.entity;

import com.likelion.tripTruth.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conflict_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConflictDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conflict_card_id", nullable = false)
    private ConflictCard conflictCard;

    @Column(nullable = false)
    private String content;

    @Builder
    public ConflictDetail(ConflictCard conflictCard, String content) {
        this.conflictCard = conflictCard;
        this.content = content;
    }
}