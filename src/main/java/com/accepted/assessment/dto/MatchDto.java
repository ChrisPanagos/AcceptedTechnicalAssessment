package com.accepted.assessment.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record MatchDto(
        Long id,
        String description,
        LocalDate matchDate,
        LocalTime matchTime,
        String teamA,
        String teamB,
        Short sport
) {}
