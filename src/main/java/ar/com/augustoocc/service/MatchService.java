package ar.com.augustoocc.service;

import ar.com.augustoocc.model.Match;

public interface MatchService {

    public Mono newLike(int id);
    public void newDislike(int id);
    public Match findMatch(int id);


}