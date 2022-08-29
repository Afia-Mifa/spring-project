package com.example.studentCrud.controller;

import com.example.studentCrud.dto.RegisterDto;
import com.example.studentCrud.model.Student;
import com.example.studentCrud.model.StudentLogin;
import com.example.studentCrud.service.AdminServiceImpl;
import com.example.studentCrud.service.RegisterServiceImpl;
import com.example.studentCrud.service.StudentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    private final AdminServiceImpl adminService;
    private final StudentServiceImpl studentService;
    private final RegisterServiceImpl registerService;

    public AdminController(AdminServiceImpl adminService, StudentServiceImpl studentService, RegisterServiceImpl registerService) {
        this.adminService = adminService;
        this.studentService = studentService;
        this.registerService = registerService;
    }

    @GetMapping("/")
    public String createAdmin() {
        adminService.createAdmin();
        return "redirect:/login/admin";
    }

    @GetMapping("/login/admin")
    public String getAdminLoginPage() {
        return "adminLogin";
    }

    @GetMapping("/admin/student/requests")
    public String getInactiveStudents(Model model) {
        model.addAttribute("inactive_students", studentService.getInactiveStudents(0));
        return "studentRequests";
    }

    @GetMapping("/admin/student/approve{id}")
    public String showStudentUpdateForm(@PathVariable(value = "id") Long id) {
        studentService.activateStudentAccount(id);
        return "redirect:/admin/student/requests";
    }

    @GetMapping("/admin/student/delete/{active}/{id}")
    public String deleteStudentRequest(@PathVariable(value = "id") Long id, @PathVariable(value = "active") int active) {

        String path = "";

        if (active == 0) {
            path = "admin/student/requests";
        } else {
            path = "admin/student";
        }
        Student student = studentService.getStudentById(id);
        StudentLogin studentLogin = studentService.getStudentLogin(student);
        studentService.deleteStudent(student);
        studentService.deleteStudentLogin(studentLogin);

        return "redirect:/" + path;
    }

    @GetMapping("admin/student")
    public String viewHomePage(Model model) {
        model.addAttribute("active_students", studentService.getAllActiveStudents());
        model.addAttribute("students", new Student());
        return "index";
    }


    @PostMapping("admin/student/search")
    public String searchStudent(@ModelAttribute(value = "students") Student student, Model model) {
        String text = student.getName();
        model.addAttribute("active_students", studentService.searchStudentByName(text));
        return "index";
    }

    @GetMapping("/admin/student/add")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("new_student", new RegisterDto());
        return "newStudent";
    }

    @PostMapping("/admin/student/save")
    public String registerNewStudent(@ModelAttribute("new_student") RegisterDto registerDto) {
        registerService.registerStudent(registerDto, 1);
        return "redirect:/admin/student";
    }

    @GetMapping("/admin/student/update/{id}")
    public String showStudentUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("/admin/student/update")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/admin/student";
    }


}
