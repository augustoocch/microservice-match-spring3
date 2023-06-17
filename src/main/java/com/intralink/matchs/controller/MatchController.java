package com.intralink.matchs.controller;

import com.intralink.matchs.dto.RequestDto;
import com.intralink.matchs.model.Match;
import com.intralink.matchs.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping(value = "/new-match")
    public Mono<Match> findCourse(@RequestBody RequestDto request) {
        return matchService.newLike(request.getId(), request.getMatchId());
    }

    @PutMapping(value = "/new-matches")
    public ResponseEntity<Match> newCourse(@RequestBody Match match) {
    return null;
    }

    @PatchMapping(value = "/update-match")
    public ResponseEntity<Match> updateUser(@RequestBody Match match) {
        return null;

    }

    @GetMapping("/get-all-users")
    public Flux<Match> getAllUsers() {
        return matchService.findAllUsers();
    }

    @DeleteMapping(value = "/delete-match")
    public ResponseEntity<Match> deleteUser(@RequestBody Match match) {
        return null;
    }
}