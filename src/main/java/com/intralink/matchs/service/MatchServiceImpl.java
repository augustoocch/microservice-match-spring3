package com.intralink.matchs.service;

import com.intralink.matchs.model.Match;
import com.intralink.matchs.repository.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MatchServiceImpl implements MatchService{

    @Autowired
    MatchRepository matchRepository;


    @Override
    public Mono<ResponseEntity<Boolean>> newLike(long idUser, long idLike) {
        Mono<ResponseEntity<Boolean>> like = matchRepository.findById(idUser)
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
                }) .flatMap(findMatch -> {
                    Mono<Boolean> matchingValidation = getMatchingValidation(idUser, idLike);

                    return matchingValidation
                            .map(result -> ResponseEntity.status(200).body(result))
                            .defaultIfEmpty(ResponseEntity.status(404).build())
                            .flatMap(responseEntity -> Mono.just(ResponseEntity.ok().body(responseEntity.getBody())));
                })
                .switchIfEmpty(Mono.error(() -> new RuntimeException("User not found")));

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

   //In this method I check the match
    public Mono<Boolean> getMatchingValidation(Long idUser, Long idLike){

        Mono<Boolean> getMatch = matchRepository.findById(idLike)
                .flatMap(userMatched -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try{
                        JsonNode jsonNode = objectMapper.readTree(userMatched.getLikes());
                        ObjectNode objectNode = (ObjectNode) jsonNode;
                        JsonNode searchValue = objectNode.get(String.valueOf(idUser));
                        if (searchValue!= null) {
                            return Mono.just(true);
                        } else {
                            return Mono.just(false);
                        }
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }
                }).map(i -> i.booleanValue())
                .switchIfEmpty(Mono.error(() -> new RuntimeException("Map Empty in method getMatchinValidation()")));

        return getMatch;
    }

    @Override
    public Flux<Match> findAllUsers() {

        Flux<Match> find = matchRepository.findAll();
        return find;
    }
}



/*
   @Override
    public Mono<ResponseEntity<Boolean>> newLike(long idUser, long idLike) {
        Mono<ResponseEntity<Boolean>> like = matchRepository.findById(idUser)
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
                }).map(findMatch -> {
                    Mono<Boolean> matchinValidation = getMatchingValidation(idUser, idLike);
                    Boolean matchBool = matchinValidation.block();
                    return ResponseEntity.status(200).body(matchBool);
                })
                .switchIfEmpty(Mono.error(() -> new RuntimeException("User not found")));

        return null;

    }


                         if (searchValue!= null) {
                            return Mono.just(true);
                        } else {
                            return Mono.just(false);
                        }

 */
