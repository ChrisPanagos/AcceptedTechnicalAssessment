package com.accepted.assessment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "match_odds")
public class MatchOdd {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odds_id_gen")
    @SequenceGenerator(name = "match_odds_id_gen", sequenceName = "match_odds_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(name = "specifier", nullable = false, length = 10)
    private String specifier;

    @Column(name = "odd", nullable = false, precision = 5, scale = 2)
    private BigDecimal odd;

}