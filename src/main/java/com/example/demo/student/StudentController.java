package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // implement POST mapping
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }
}
