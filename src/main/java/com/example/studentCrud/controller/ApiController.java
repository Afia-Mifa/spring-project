package com.example.studentCrud.controller;

import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.model.Student;
import com.example.studentCrud.service.RegisterServiceImpl;
import com.example.studentCrud.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiController {

    private final StudentService studentService;
//    private final StudentRegRepo studentRegRepo;
    private final RegisterServiceImpl registerService;

    public ApiController(StudentService studentService, RegisterServiceImpl registerService) {
        this.studentService = studentService;
        this.registerService = registerService;
    }

    @GetMapping("/student")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        model.addAttribute("students", new Student());
        return "index";
    }

    @GetMapping("/showNewStudent")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/studentUpdateForm/{id}")
    public String showStudentUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "update_student";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id) {
        Student student = studentService.getStudentById(id);
        studentService.deleteStudent(student);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchStudent(@ModelAttribute(value = "students") Student student, Model model) {
        String text = student.getName();
        model.addAttribute("listStudents", studentService.searchStudentByName(text));
        return "index";
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        model.addAttribute("new_student", new RegisterDto());
        return "register";
    }

    @PostMapping("/save")
    public String registerNewStudent(@ModelAttribute("new_student") RegisterDto registerDto){
        registerService.RegisterStudent(registerDto);
        return "redirect:/register?success";

    }

}
