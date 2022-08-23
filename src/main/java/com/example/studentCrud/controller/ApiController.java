package com.example.studentCrud.controller;

import com.example.studentCrud.Repository.StudentRepo;
import com.example.studentCrud.model.Student;
import com.example.studentCrud.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApiController {

    private final StudentService studentService;

    public ApiController(StudentService studentService ) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listStudents",studentService.getAllStudents());
        return "index";
    }

    @GetMapping("/showNewStudent")
    public String showNewStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/studentUpdateForm/{id}")
    public String showStudentUpdateForm(@PathVariable (value = "id") Long id, Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "update_student";
    }

    @PostMapping ("/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable (value = "id") Long id){
        Student student =  studentService.getStudentById(id);
        studentService.deleteStudent(student);
        return "redirect:/";
    }

}
