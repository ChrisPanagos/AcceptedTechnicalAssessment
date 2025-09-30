package com.accepted.assessment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_id_gen")
    @SequenceGenerator(name = "match_id_gen", sequenceName = "match_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name = "match_time", nullable = false)
    private LocalTime matchTime;

    @Column(name = "team_a", nullable = false, length = 100)
    private String teamA;

    @Column(name = "team_b", nullable = false, length = 100)
    private String teamB;

    @Column(name = "sport", nullable = false)
    private Short sport;

}