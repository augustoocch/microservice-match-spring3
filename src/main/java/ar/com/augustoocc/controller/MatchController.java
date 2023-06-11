package ar.com.augustoocc.controller;

import ar.com.augustoocc.model.Match;
import ar.com.augustoocc.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping(value = "/new-match")
    public Mono<Match> findCourse(@RequestBody long id, long matchId) {
        return matchService.newLike(id, matchId);
    }

    @PutMapping(value = "/new-match")
    public ResponseEntity<Match> newCourse(@RequestBody Match match) {
    return null;
    }

    @PatchMapping(value = "/update-match")
    public ResponseEntity<Match> updateUser(@RequestBody Match match) {
        return null;

    }

    @DeleteMapping(value = "/delete-match")
    public ResponseEntity<Match> deleteUser(@RequestBody Match match) {
        return null;
    }
}