package com.accepted.assessment.service;

import com.accepted.assessment.dto.MatchDto;
import com.accepted.assessment.dto.MatchOddDto;

import java.util.List;

public interface MatchService {

    // Match
    List<MatchDto> getAllMatches();
    MatchDto getMatchById(Long id);
    MatchDto createMatch(MatchDto matchDto);
    MatchDto updateMatch(Long id, MatchDto matchDto);
    void deleteMatch(Long id);

    // Match Odds
    List<MatchOddDto> getMatchOddsByMatchId(Long matchId);
    MatchOddDto createOdds(Long matchId, MatchOddDto matchOddDto);
    MatchOddDto getOddsByIdAndMatchId(Long matchId, Long matchOddId);
    MatchOddDto updateOdds(Long matchId, Long matchOddId, MatchOddDto matchOddDto);
    void deleteOdds(Long matchId, Long oddId);
}