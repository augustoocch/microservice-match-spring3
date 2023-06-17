package com.intralink.matchs.service;

import com.intralink.matchs.model.Match;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface MatchService {

    public Mono<Match> newLike(long id, long idMatch);
    public Mono<Match> newDislike(long id, long idMatch);
    public Flux<Match> findAllUsers();

}