package ar.com.augustoocc.service;

import ar.com.augustoocc.model.Course;
import ar.com.augustoocc.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseService {


    public void deleteCourse(Course course);
    public void createCourse(Course course);
    public void updateCourse(Course course);
    public Course findCourse(String name);
}