package ar.com.augustoocc.service;

import ar.com.augustoocc.model.Course;

public interface CourseService {


    public void deleteCourse(Course course);
    public void createCourse(Course course);
    public void updateCourse(Course course);
    public Course findCourse(String name);
}