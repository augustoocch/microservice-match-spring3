package com.intralink.matchs.repository;

import com.intralink.matchs.model.Match;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends R2dbcRepository<Match, Long> {
}
