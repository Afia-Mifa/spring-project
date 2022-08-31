package com.example.studentCrud.service;

import com.example.studentCrud.dto.CourseDto;
import com.example.studentCrud.model.Course;

import java.util.List;

public interface courseService {
    List<Course> findAllCourse();
    void saveCourse(CourseDto courseDto);
    Course getCourseById(Long id) throws Exception;
    void updateCourse(CourseDto courseDto) throws Exception;
    void deleteCourse(Long id) throws Exception;
    List<Course> searchCourseByName(String courseName);
    CourseDto fromCourseToDto(Course course);
}
