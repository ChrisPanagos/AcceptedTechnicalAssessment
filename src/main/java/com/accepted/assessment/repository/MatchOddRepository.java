package com.accepted.assessment.repository;

import com.accepted.assessment.entity.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MatchOddRepository extends JpaRepository<MatchOdd, Long> {
    List<MatchOdd> findByMatchId(Long matchId);
    Optional<MatchOdd> findByIdAndMatchId(Long id, Long matchId);

    boolean existsByIdAndMatchId(Long id, Long matchId);
}