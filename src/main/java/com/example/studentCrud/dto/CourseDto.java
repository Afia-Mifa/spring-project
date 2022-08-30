package com.example.studentCrud.dto;

import com.example.studentCrud.model.Course;

public class CourseDto {
    private Long id;
    private String courseName;

    public CourseDto(Long id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    public CourseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public static CourseDto from(Course course){
        return new CourseDto(course.getId(),course.getCourseName());
    }
}
