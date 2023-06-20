package com.intralink.matchs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.intralink.matchs.model.Match;
import com.intralink.matchs.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MatchServiceImpl implements MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Override
    public Mono<String> newLike(long idUser, long idLike) {
        Mono<String> like = matchRepository.findById(idUser)
                .flatMap(match -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = objectMapper.readTree(match.getLikes());
                        ObjectNode objectNode = (ObjectNode) jsonNode;
                        objectNode.put("idUsr", String.valueOf(idLike));
                        match.setLikes(objectMapper.writeValueAsString(objectNode));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return matchRepository.save(match);
                }).map(match -> match.getLikes());
        return like;

    }

    @Override
    public Mono<String> newDislike(long idUser, long idDislike) {
        Mono<String> dislike = matchRepository.findById(idUser)
                .flatMap(match -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = objectMapper.readTree(match.getDislikes());
                        ObjectNode objectNode = (ObjectNode) jsonNode;
                        objectNode.put("id", String.valueOf(idDislike));
                        match.setDislikes(objectMapper.writeValueAsString(objectNode));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return matchRepository.save(match);
                }).map(match -> match.getDislikes());
        return dislike;
    }

    @Override
    public Flux<Match> findAllUsers() {

        Flux<Match> find = matchRepository.findAll();
        return find;
    }
}