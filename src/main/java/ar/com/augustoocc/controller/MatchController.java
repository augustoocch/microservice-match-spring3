package ar.com.augustoocc.controller;


import ar.com.augustoocc.model.Course;
import ar.com.augustoocc.model.Match;
import ar.com.augustoocc.service.CourseService;
import ar.com.augustoocc.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping(value = "/find-match/{match}")
    public ResponseEntity<Match> findCourse(@PathVariable(name = "match", required = true) String matchName) {
        if (matchService.findCourse(matchName) != null) {
            Match curso = new Match();
            curso = matchService.findCourse(matchName);
            return new ResponseEntity("Course: " + curso.getNombre() + " found", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Course not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/new-match")
    public ResponseEntity<Match> newCourse(@RequestBody Match match) {
        if (matchService.findCourse(match.getNombre()) != null) {
            return new ResponseEntity("Course already exist!!", HttpStatus.BAD_REQUEST);
        } else {
            matchService.createCourse(match);
            return new ResponseEntity("Course created with name: " + match.getNombre() + ", and id: " + match.getIdCurso(), HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping(value = "/update-match")
    public ResponseEntity<Match> updateUser(@RequestBody Match match) {
        if (matchService.findCourse(match.getNombre()) != null) {
            Course newCourse = matchService.findCourse(match.getNombre());
            matchService.updateCourse(match);
            return new ResponseEntity("Course: " + match.getNombre() + " properly updated", HttpStatus.ACCEPTED);
        }
        if (matchService.findCourse(match.getNombre()) == null) {
            return new ResponseEntity("Course doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Course couldn't be updated", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/delete-match")
    public ResponseEntity<Match> deleteUser(@RequestBody Match match) {
        if (matchService.findCourse(match.getNombre()) != null) {
            Match newCourse = matchService.findCourse(match.getNombre());
            matchService.deleteCourse(match);
            return new ResponseEntity("Course: " + match.getNombre() + " properly deleted", HttpStatus.ACCEPTED);
        }
        if (matchService.findCourse(match.getNombre()) == null) {
            return new ResponseEntity("Course doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Course couldn't be deleted", HttpStatus.BAD_REQUEST);
        }
    }
}