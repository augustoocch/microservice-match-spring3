package ar.com.augustoocc.service;

import ar.com.augustoocc.model.Match;
import ar.com.augustoocc.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

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
