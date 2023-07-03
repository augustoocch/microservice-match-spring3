package com.intralink.matchs.service;

import com.intralink.matchs.model.Match;
import com.intralink.matchs.repository.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import reactor.bus.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MatchServiceImpl implements MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    EventBus eventBus;

    @Override
    public Mono<String> newLike(long idUser, long idLike) {
        Mono<String> like = matchRepository.findById(idUser)
                .flatMap(match -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        if (match.getLikes() == null) {
                            ObjectNode objectNode = objectMapper.createObjectNode();
                            objectNode.put(String.valueOf(idLike), String.valueOf(idLike));
                            match.setLikes(objectMapper.writeValueAsString(objectNode));
                        } else {
                            JsonNode jsonNode = objectMapper.readTree(match.getLikes());
                            ObjectNode objectNode = (ObjectNode) jsonNode;
                            objectNode.put(String.valueOf(idLike), String.valueOf(idLike));
                            match.setLikes(objectMapper.writeValueAsString(objectNode));
                        }
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return matchRepository.save(match);
                }).map(match -> match.getLikes())
                .switchIfEmpty(Mono.error(() -> new RuntimeException("User not found")));

        eventBus.notify();
        return like;

    }

    @Override
    public Mono<String> newDislike(long idUser, long idDislike) {
        Mono<String> dislike = matchRepository.findById(idUser)
                .flatMap(match -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        if (match.getDislikes() == null) {
                            ObjectNode objectNode = objectMapper.createObjectNode();
                            objectNode.put(String.valueOf(idDislike), String.valueOf(idDislike));
                            match.setDislikes(objectMapper.writeValueAsString(objectNode));
                        } else{
                            JsonNode jsonNode = objectMapper.readTree(match.getDislikes());
                            ObjectNode objectNode = (ObjectNode) jsonNode;
                            objectNode.put(String.valueOf(idDislike), String.valueOf(idDislike));
                            match.setDislikes(objectMapper.writeValueAsString(objectNode));
                        }
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return matchRepository.save(match);
                }).map(match -> match.getDislikes())
                .switchIfEmpty(Mono.error(()-> new RuntimeException("User not found")));
        return dislike;
    }

    @Override
    public Flux<Match> findAllUsers() {

        Flux<Match> find = matchRepository.findAll();
        return find;
    }
}