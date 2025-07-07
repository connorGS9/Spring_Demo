package com.connorDev.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service //Denote this class as a service component
public class StudentService { //Service layer for students
private final StudentRepository studentRepository; //Repository interface
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() { //Get mapping for student controller
        return studentRepository.findAll(); //Interface method to get list of ALL students
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email is taken"); //Simple exception catch for POST request adding new user with already taken email
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("Student with id " + studentId + "doesn't exist.");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalArgumentException("Student does not exist!"));
        //Check if name/email is null and if email is taken before updating student details
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Please enter the new name to update to");
        } else {
            student.setName(name);
        }

        if(email == null || email.length() < 3) {
            throw new IllegalArgumentException("Please enter a valid email to update to");
        } else {
            student.setEmail(email);
        }
    }
}
