package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    private final StudentService studentService;

    // magically instantiates studentService and injects into the constructor
    @Autowired // dependency injection, so dont need to do new studentService
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping // get endpoint
    public List<Student> getStudents() {
        // use method in StudentService
        return studentService.getStudents();
    }
}