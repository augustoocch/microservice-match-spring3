package ar.com.augustoocc.service;

import ar.com.augustoocc.model.Match;
import reactor.core.publisher.Mono;

public interface MatchService {

    public Mono<Match> newLike(long id, long idMatch);
    public void newDislike(int id);
    public Match findMatch(int id);


}