package com.accepted.assessment.service;

import com.accepted.assessment.dto.MatchDto;
import com.accepted.assessment.dto.MatchOddDto;
import com.accepted.assessment.entity.Match;
import com.accepted.assessment.entity.MatchOdd;
import com.accepted.assessment.repository.MatchOddRepository;
import com.accepted.assessment.repository.MatchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchOddRepository matchOddRepository;

    public MatchServiceImpl(MatchRepository matchRepository, MatchOddRepository matchOddRepository) {
        this.matchRepository = matchRepository;
        this.matchOddRepository = matchOddRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchDto> getAllMatches() {
        return matchRepository.findAll()
                .stream()
                .map(this::toMatchDto)
                .collect(Collectors.toList());
    }

    @Override
    public MatchDto getMatchById(Long id) {
        Match match = matchRepository.findById(id).orElseThrow(() -> notFound("Match", id));
        return toMatchDto(match);
    }

    @Override
    public MatchDto createMatch(MatchDto matchDto) {
        Match match = toMatchEntity(matchDto);
        match.setId(null);
        Match saved = matchRepository.save(match);
        return toMatchDto(saved);
    }

    @Override
    public MatchDto updateMatch(Long id, MatchDto matchDto) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> notFound("Match", id));

        // update fields
        match.setDescription(matchDto.description());
        match.setMatchDate(matchDto.matchDate());
        match.setMatchTime(matchDto.matchTime());
        match.setTeamA(matchDto.teamA());
        match.setTeamB(matchDto.teamB());
        match.setSport(matchDto.sport());

        Match saved = matchRepository.save(match);
        return toMatchDto(saved);
    }

    @Override
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw notFound("Match", id);
        }
        matchRepository.deleteById(id);
    }

    @Override
    public List<MatchOddDto> getMatchOddsByMatchId(Long matchId) {
        return matchOddRepository.findByMatchId(matchId)
                .stream()
                .map(this::toMatchOddDto)
                .collect(Collectors.toList());
    }

    @Override
    public MatchOddDto createOdds(Long matchId, MatchOddDto matchOddDto) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> notFound("Match", matchId));

        MatchOdd odd = new MatchOdd();
        odd.setId(null);
        odd.setMatch(match);
        odd.setSpecifier(matchOddDto.specifier());
        odd.setOdd(matchOddDto.odd());

        MatchOdd saved = matchOddRepository.save(odd);
        return toMatchOddDto(saved);
    }

    @Override
    public MatchOddDto getOddsByIdAndMatchId(Long matchId, Long matchOddId) {
        if (!matchRepository.existsById(matchId)) {
            throw notFound("Match", matchId);
        }
        MatchOdd matchOdd = matchOddRepository.findByIdAndMatchId(matchOddId, matchId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("MatchOdd with id %d not found for match with id %d", matchOddId, matchId)
                        )
                );
        return toMatchOddDto(matchOdd);
    }

    @Override
    public MatchOddDto updateOdds(Long matchId, Long matchOddId, MatchOddDto matchOddDto) {
        MatchOdd matchOdd = matchOddRepository.findByIdAndMatchId(matchOddId, matchId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("MatchOdd with id %d not found for match %d", matchOddId, matchId)
                ));

        matchOdd.setSpecifier(matchOddDto.specifier());
        matchOdd.setOdd(matchOddDto.odd());

        MatchOdd saved = matchOddRepository.save(matchOdd);
        return toMatchOddDto(saved);
    }

    @Override
    public void deleteOdds(Long matchId, Long matchOddId) {
        boolean exists = matchOddRepository.existsByIdAndMatchId(matchOddId, matchId);
        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("MatchOdd with id %d not found for match %d", matchOddId, matchId)
            );
        }
        matchOddRepository.deleteById(matchOddId);
    }

    private MatchDto toMatchDto(Match e) {
        return new MatchDto(
                e.getId(),
                e.getDescription(),
                e.getMatchDate(),
                e.getMatchTime(),
                e.getTeamA(),
                e.getTeamB(),
                e.getSport()
        );
    }

    private Match toMatchEntity(MatchDto dto) {
        Match match = new Match();
        match.setId(dto.id());
        match.setDescription(dto.description());
        match.setMatchDate(dto.matchDate());
        match.setMatchTime(dto.matchTime());
        match.setTeamA(dto.teamA());
        match.setTeamB(dto.teamB());
        match.setSport(dto.sport());
        return match;
    }

    private MatchOddDto toMatchOddDto(MatchOdd e) {
        return new MatchOddDto(
            e.getId(),
            e.getSpecifier(),
            e.getOdd()
        );
    }

    private MatchOdd toOddEntity(MatchOddDto dto) {
        MatchOdd matchOdd = new MatchOdd();
        matchOdd.setId(dto.id());
        matchOdd.setSpecifier(dto.specifier());
        matchOdd.setOdd(dto.odd());
        return matchOdd;
    }

    private ResponseStatusException notFound(String type, Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, type + " not found: " + id);
    }
}
