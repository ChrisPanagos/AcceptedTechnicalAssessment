package com.accepted.assessment.repository;

import com.accepted.assessment.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

}