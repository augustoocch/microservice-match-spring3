package ar.com.augustoocc.controller;


import ar.com.augustoocc.model.Course;
import ar.com.augustoocc.model.Match;
import ar.com.augustoocc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @Autowired
    CourseService courseService;

    @GetMapping(value = "/find-course/{course}")
    public ResponseEntity<Match> findCourse(@PathVariable(name = "course", required = true) String courseName) {
        if (courseService.findCourse(courseName) != null) {
            Match curso = new Match();
            curso = courseService.findCourse(courseName);
            return new ResponseEntity("Course: " + curso.getNombre() + " found", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Course not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/new-course")
    public ResponseEntity<Match> newCourse(@RequestBody Match course) {
        if (courseService.findCourse(course.getNombre()) != null) {
            return new ResponseEntity("Course already exist!!", HttpStatus.BAD_REQUEST);
        } else {
            courseService.createCourse(course);
            return new ResponseEntity("Course created with name: " + course.getNombre() + ", and id: " + course.getIdCurso(), HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping(value = "/update-course")
    public ResponseEntity<Match> updateUser(@RequestBody Match course) {
        if (courseService.findCourse(course.getNombre()) != null) {
            Course newCourse = courseService.findCourse(course.getNombre());
            courseService.updateCourse(course);
            return new ResponseEntity("Course: " + course.getNombre() + " properly updated", HttpStatus.ACCEPTED);
        }
        if (courseService.findCourse(course.getNombre()) == null) {
            return new ResponseEntity("Course doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Course couldn't be updated", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/delete-course")
    public ResponseEntity<Match> deleteUser(@RequestBody Match course) {
        if (courseService.findCourse(course.getNombre()) != null) {
            Match newCourse = courseService.findCourse(course.getNombre());
            courseService.deleteCourse(course);
            return new ResponseEntity("Course: " + course.getNombre() + " properly deleted", HttpStatus.ACCEPTED);
        }
        if (courseService.findCourse(course.getNombre()) == null) {
            return new ResponseEntity("Course doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Course couldn't be deleted", HttpStatus.BAD_REQUEST);
        }
    }
}