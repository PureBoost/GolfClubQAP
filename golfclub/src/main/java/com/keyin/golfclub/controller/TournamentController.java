package com.keyin.golfclub.controller;

import com.keyin.golfclub.model.Tournament;
import com.keyin.golfclub.repository.TournamentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentRepository tournamentRepository;

    public TournamentController(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
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