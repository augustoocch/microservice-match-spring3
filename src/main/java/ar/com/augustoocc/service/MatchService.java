package ar.com.augustoocc.service;

import ar.com.augustoocc.model.Match;

public interface MatchService {

    public void newLike(Match course);
    public void newDislike(Match course);
    public Match findMatch(String name);


}