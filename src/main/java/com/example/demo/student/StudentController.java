package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
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

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long id,
//            @RequestBody HashMap<String, String> updateParams
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String email) {
        studentService.updateStudent(id, name, email);
    }
}
