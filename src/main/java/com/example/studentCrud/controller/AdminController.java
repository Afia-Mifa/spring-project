package com.example.studentCrud.controller;

import com.example.studentCrud.dto.CourseDto;
import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.dto.UserDto;
import com.example.studentCrud.model.Course;
import com.example.studentCrud.model.User;
import com.example.studentCrud.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final UserServiceImpl userService;
    private final courseServiceImpl courseService;

    public AdminController(UserServiceImpl userService, courseServiceImpl courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String createAdmin() {
        userService.createAdmin();
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getAdminLoginPage() {
        return "login";
    }

    @GetMapping("/admin/student")
    public String viewHomePage(Model model) {
        model.addAttribute("active_students", userService.findAllStudents());
        model.addAttribute("students", new User());
        return "index";
    }

    @GetMapping("/admin/student/requests")
    public String getInactiveStudents(Model model) {
        model.addAttribute("inactive_students", userService.getStudentByActive(false));
        return "studentRequests";
    }

    @GetMapping("/admin/student/approve{id}")
    public String showStudentUpdateForm(@PathVariable(value = "id") Long id) {
        userService.activateStudentAccount(id);
        return "redirect:/admin/student/requests";
    }

    @GetMapping("/admin/student/delete/{active}/{id}")
    public String deleteStudentRequest(@PathVariable(value = "id") Long id, @PathVariable(value = "active") boolean active) {
        String path = "";
        if (active == false) {
            path = "admin/student/requests";
        } else {
            path = "admin/student";
        }
        User user = userService.getStudentById(id);
        userService.deleteStudent(user);
        return "redirect:/" + path;
    }

    @PostMapping("admin/student/search")
    public String searchStudent(@ModelAttribute(value = "students") UserDto user, Model model) {
        String text = user.getName();
        model.addAttribute("active_students", userService.searchStudentByName(text));
        return "index";
    }

    @GetMapping("/admin/student/add")
    public String showNewStudentForm(Model model) {
        model.addAttribute("new_student", new RegisterDto());
        return "newStudent";
    }

    @PostMapping("/admin/student/save")
    public String registerNewStudent(@ModelAttribute("new_student") RegisterDto registerDto) {
        userService.registerStudent(registerDto, true);
        return "redirect:/admin/student";
    }


    @GetMapping("/admin/student/update/{id}")
    public String showStudentUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.getStudentById(id);
        model.addAttribute("student", userService.fromUserToDto(user));
        return "updateStudent";
    }

    @PostMapping("/admin/student/update")
    public String updateStudent(@ModelAttribute("student") UserDto user) throws Exception {
        userService.updateStudent(user);
        return "redirect:/admin/student";
    }

    @GetMapping("/course")
    public String viewCoursePage(Model model) {
        model.addAttribute("courses", courseService.findAllCourse());
        model.addAttribute("course", new Course());
        return "course";
    }

    @GetMapping("/admin/course/add")
    public String addNewCourse(Model model) {
        model.addAttribute("course", new CourseDto());
        return "add_course";
    }

    @PostMapping("/admin/course/save")
    public String saveNewCourse(@ModelAttribute(value = "course") CourseDto courseDto) {
        courseService.saveCourse(courseDto);
        return "redirect:/course";
    }

    @GetMapping("/admin/course/update/{id}")
    public String showCourseUpdateForm(@PathVariable(value = "id") Long id, Model model) throws Exception {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", courseService.fromCourseToDto(course));
        return "update_course";
    }

    @PostMapping("/admin/course/update")
    public String updateCourse(@ModelAttribute(value = "course") CourseDto courseDto) throws Exception {
        courseService.updateCourse(courseDto);
        return "redirect:/course";
    }

    @GetMapping("/admin/course/delete/{id}")
    public String deleteCoursePage(@PathVariable(value = "id") Long id) throws Exception {
        courseService.deleteCourse(id);
        return "redirect:/course";
    }

    @PostMapping("/course/search")
    public String searchCourse(@ModelAttribute(value = "course") CourseDto course, Model model) {
        String text = course.getCourseName();
        model.addAttribute("courses", courseService.searchCourseByName(text));
        return "course";
    }

}
