package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// component allows for IoC, for the StudentController to automatically instantiate it
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
//        return List.of(
//                new Student(1L, "Mariam", 21, LocalDate.of(2000, Month.JANUARY, 5), "mariam.jamal@gmail.com")
//        );
        return studentRepository.findAll();
    }
}