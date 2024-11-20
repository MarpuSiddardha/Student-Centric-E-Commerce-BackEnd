package com.taskmanager.Service;

import com.taskmanager.Entity.Student;
import com.taskmanager.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class StudentService {

    private final Repo studentRepository;

    @Autowired
    public StudentService(Repo studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }


    public Optional<Student> getStudentByPassword(String password) {
        return studentRepository.findByPassword(password);
    }

    public void deleteStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Student with ID " + id + " does not exist.");
        }
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setPassword(updatedStudent.getPassword());
            existingStudent.setCnfpwd(updatedStudent.getCnfpwd());
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new IllegalArgumentException("Student with ID " + id + " does not exist."));
    }
}
