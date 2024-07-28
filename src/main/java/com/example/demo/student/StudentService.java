package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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

    // transactional is used so don't have to use jpql query, so can use the setters in the Entity to check whether can or cannot update
    // use setters to automatically update the Entity when possible
    // will make the entity go into a managed state
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));

        // update name if certain conditions are met
        if (
                name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)
        ) {
            student.setName(name);
        }
        // update email if certain conditions are met + email not taken
        if (
                email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)
        ) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            // checks if email already in database
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

//        String newName = updateParams.get("name");
//        if (newName != null) {
//            System.out.println(newName);
//        }
//        String newEmail = updateParams.get("email");
//        if (newEmail != null) {
//            System.out.println(newEmail);
//        }
    }
}
