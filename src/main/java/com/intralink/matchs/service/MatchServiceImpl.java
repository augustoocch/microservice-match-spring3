package com.intralink.matchs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.intralink.matchs.model.Match;
import com.intralink.matchs.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MatchServiceImpl implements MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Override
    public Mono<Match> newLike(long idUser, long idLike) {

        Mono<Match> like = matchRepository.findById(idUser)
                .flatMap(match -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = objectMapper.readTree(match.getLike());
                        ArrayNode arrayNode = (ArrayNode) jsonNode;
                        arrayNode.add(idLike);
                        match.setLike(objectMapper.writeValueAsString(arrayNode));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return matchRepository.save(match);
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