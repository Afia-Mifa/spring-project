package com.example.studentCrud.service;

import com.example.studentCrud.Repository.CourseRepo;
import com.example.studentCrud.dto.CourseDto;
import com.example.studentCrud.model.Course;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class courseServiceImpl implements courseService{
    private final CourseRepo courseRepo;
    private final ModelMapper modelMapper;

    public courseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepo.findAll();
    }

    @Override
    public void saveCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        courseRepo.save(course);

    }

    @Override
    public Course getCourseById(Long id) throws Exception {
        Optional<Course> courseOptional = courseRepo.findById(id);
        Course course;
        if (courseOptional.isPresent()){
           course = courseOptional.get();
        }else{
            throw new Exception("User Not Found");
        }
        return course;
    }

    @Override
    public void updateCourse(CourseDto courseDto) throws Exception {
        Course existingCourse = courseRepo.findById(courseDto.getId()).orElse(null);
        if (Objects.nonNull(existingCourse)){
            existingCourse.setCourseName(courseDto.getCourseName());
            courseRepo.save(existingCourse);
        } else {
            throw new Exception("User Not Found");
        }
    }

    @Override
    public void deleteCourse(Long id) throws Exception {
        Course existingCourse = courseRepo.findById(id).orElse(null);
        if (Objects.nonNull(existingCourse)){
            courseRepo.delete(existingCourse);
        } else {
            throw new Exception("Course Does Not Exist");
        }
    }

    @Override
    public List<Course> searchCourseByName(String courseName) {
        List<Course> courses = courseRepo.findAllByCourseName(courseName);
        return courses;
    }

    @Override
    public CourseDto fromCourseToDto(Course course){
        CourseDto courseDto = modelMapper.map(course,CourseDto.class);
        return courseDto;
    }
}
