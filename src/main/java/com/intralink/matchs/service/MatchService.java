package com.intralink.matchs.service;

import com.intralink.matchs.model.Match;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface MatchService {

    public Mono<Match> newLike(long id, long idMatch);
    public void newDislike(int id);
    public Match findMatch(int id);


}