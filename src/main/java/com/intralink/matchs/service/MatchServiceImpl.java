package com.intralink.matchs.service;

import com.intralink.matchs.model.Match;
import com.intralink.matchs.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Component
public class MatchServiceImpl implements MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Override
    public Mono<Match> newLike(long idUser, long idLike) {

        Mono<Match> like = matchRepository.findById(idUser)
                .map(i -> {
                    i.getLike().add(idLike);
                    return i;
                });
        return like;
    }

    @Override
    public void newDislike(int id) {

    }

    @Override
    public Match findMatch(int id) {
        return null;
    }
}
