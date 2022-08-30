package com.example.studentCrud.Repository;

import com.example.studentCrud.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {
 List<Course> findAllByCourseName(String courseName);
}