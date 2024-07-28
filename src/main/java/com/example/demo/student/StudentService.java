package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

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
//                new Student("Mariam", LocalDate.of(2000, Month.JANUARY, 5), "mariam.jamal@gmail.com")
//        );
        return studentRepository.findAll();
    }

    // method to add new students
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        // checks if email already in database
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        // else, save it
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
//        studentRepository.findById(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
}
