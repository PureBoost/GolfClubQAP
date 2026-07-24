package com.keyin.golfclub.repository;

import com.keyin.golfclub.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
