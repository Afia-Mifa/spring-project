package com.example.studentCrud.controller;

import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

//    private final StudentService studentService;
//    private final RegisterServiceImpl registerService;
//    private final UserServiceImpl userService;
//
//    public StudentController(StudentService studentService, RegisterServiceImpl registerService, UserServiceImpl userService) {
//        this.studentService = studentService;
//        this.registerService = registerService;
//        this.userService = userService;
//    }
//
//    @GetMapping("/student/home")
//    public String viewHomePage(Model model) {
//        model.addAttribute("active_students", studentService.getAllActiveStudents());
//        model.addAttribute("students", new Student());
//        return "page";
//    }
//
////    @GetMapping("/studentUpdateForm/{id}")
////    public String showStudentUpdateForm(@PathVariable(value = "id") Long id, Model model) {
////        Student student = studentService.getStudentById(id);
////        model.addAttribute("student", student);
////        return "updateStudent";
////    }
//
//    @PostMapping("/updateStudent")
//    public String updateStudent(@ModelAttribute("student") Student student) {
//        studentService.updateStudent(student);
//        return "redirect:/";
//    }
//
//    @GetMapping("delete/{id}")
//    public String deleteStudent(@PathVariable(value = "id") Long id) {
//        Student student = studentService.getStudentById(id);
//        studentService.deleteStudent(student);
//        return "redirect:/";
//    }
//
//    @PostMapping("/search")
//    public String searchStudent(@ModelAttribute(value = "students") Student student, Model model) {
//        String text = student.getName();
//        model.addAttribute("active_students", studentService.searchStudentByName(text));
//        return "index";
//    }
//    @GetMapping("/student/login")
//    public String getLoginPage(){
//        return "studentLogin";
//    }
//    @GetMapping("/register")
//    public String getRegistrationPage(Model model){
//        model.addAttribute("new_student", new RegisterDto());
//        return "register";
//    }
//
//    @PostMapping("/save")
//    public String registerNewStudent(@ModelAttribute("new_student") RegisterDto registerDto){
//        userService.registerStudent(registerDto,0);
//        return "redirect:/register?success";
//    }

}
