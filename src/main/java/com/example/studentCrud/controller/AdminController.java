package com.example.studentCrud.controller;

import com.example.studentCrud.model.Student;
import com.example.studentCrud.service.AdminServiceImpl;
import com.example.studentCrud.service.StudentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final AdminServiceImpl adminService;
    private final StudentServiceImpl studentService;

    public AdminController(AdminServiceImpl adminService, StudentServiceImpl studentService) {
        this.adminService = adminService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String createAdmin(){
        adminService.createAdmin();
        return "redirect:/login/admin";
    }

    @GetMapping("/login/admin")
    public String getAdminLoginPage(){
        return "adminLogin";
    }

    @GetMapping("/admin/student/requests")
    public String getInactiveStudents(Model model){
        model.addAttribute("inactive_students",studentService.getInactiveStudents(0));
        return "studentRequests";
    }

    @GetMapping("/admin/student/approve{id}")
    public String showStudentUpdateForm(@PathVariable(value = "id") Long id) {
        studentService.activateStudentAccount(id);
        return "redirect:/admin/student/requests";
    }

    @GetMapping("/admin/student/delete/{id}")
    public String deleteStudentRequest(@PathVariable(value = "id") Long id) {
        Student student = studentService.getStudentById(id);
        studentService.deleteStudent(student);
        return "redirect:/admin/student/requests";
    }

    @GetMapping("admin/student")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        model.addAttribute("students", new Student());
        return "index";
    }

}
