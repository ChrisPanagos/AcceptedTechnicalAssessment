package com.accepted.assessment.controller;

import com.accepted.assessment.dto.MatchDto;
import com.accepted.assessment.dto.MatchOddDto;
import com.accepted.assessment.entity.Match;
import com.accepted.assessment.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping
    public List<MatchDto> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public MatchDto getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatchDto createMatch(@RequestBody MatchDto matchDto) {
        return matchService.createMatch(matchDto);
    }

    @PutMapping("/{id}")
    public MatchDto updateMatchById(@PathVariable Long id, @RequestBody MatchDto matchDto) {
        return matchService.updateMatch(id, matchDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatchById(@PathVariable Long id) {
        matchService.deleteMatch(id);
    }

    @GetMapping("/{matchId}/odds")
    public List<MatchOddDto> getMatchOddsByMatchId(@PathVariable Long matchId) {
        return matchService.getMatchOddsByMatchId(matchId);
    }

    @PostMapping("/{matchId}/odds")
    @ResponseStatus(HttpStatus.CREATED)
    public MatchOddDto createMatchOddsByMatchId(@PathVariable Long matchId, @RequestBody MatchOddDto matchOddDto) {
        return matchService.createOdds(matchId, matchOddDto);
    }

    @GetMapping("/{matchId}/odds/{matchOddId}")
    public MatchOddDto getMatchOddsByMatchIdAndOddsId(@PathVariable Long matchId, @PathVariable Long matchOddId) {
        return matchService.getOddsByIdAndMatchId(matchId, matchOddId);
    }

    @PutMapping("/{matchId}/odds/{matchOddId}")
    public MatchOddDto updateMatchOddsByMatchIdAndOddsId(@PathVariable Long matchId, @PathVariable Long matchOddId, @RequestBody MatchOddDto matchOddDto) {
        return matchService.updateOdds(matchId, matchOddId, matchOddDto);
    }

    @DeleteMapping("/{matchId}/odds/{matchOddId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatchOddsByMatchIdAndOddsId(@PathVariable Long matchId, @PathVariable Long matchOddId) {
        matchService.deleteOdds(matchId, matchOddId);
    }
}