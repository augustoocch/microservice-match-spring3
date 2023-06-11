package com.intralink.matchs.repository;

import com.intralink.matchs.model.Match;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends ReactiveCrudRepository<Match, Long> {
}
