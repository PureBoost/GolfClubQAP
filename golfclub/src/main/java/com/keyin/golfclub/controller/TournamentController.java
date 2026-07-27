package com.keyin.golfclub.controller;

import com.keyin.golfclub.model.Tournament;
import com.keyin.golfclub.repository.TournamentRepository;
import org.springframework.web.bind.annotation.*;

import com.keyin.golfclub.model.Member;
import com.keyin.golfclub.repository.MemberRepository;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    public TournamentController(TournamentRepository tournamentRepository, MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @PostMapping("/{tournamentId}/members/{memberId}")
    public Tournament registerMember(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElse(null);

        Member member = memberRepository.findById(memberId)
                .orElse(null);

        if (tournament != null && member != null) {
            tournament.getParticipatingMembers().add(member);
            return tournamentRepository.save(tournament);
        }

        return null;
    }

    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament updatedTournament) {

        return tournamentRepository.findById(id)
                .map(tournament -> {
                    tournament.setStartDate(updatedTournament.getStartDate());
                    tournament.setEndDate(updatedTournament.getEndDate());
                    tournament.setLocation(updatedTournament.getLocation());
                    tournament.setEntryFee(updatedTournament.getEntryFee());
                    tournament.setCashPrize(updatedTournament.getCashPrize());

                    return tournamentRepository.save(tournament);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentRepository.deleteById(id);
    }
}