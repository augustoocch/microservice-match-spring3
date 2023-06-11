package ar.com.augustoocc.repository;

import ar.com.augustoocc.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends ReactiveCrudRepository<Match, Long> {
}
