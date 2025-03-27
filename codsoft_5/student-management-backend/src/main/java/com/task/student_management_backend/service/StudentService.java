package com.task.student_management_backend.service;

import com.task.student_management_backend.dto.StudentDto;
import com.task.student_management_backend.exception.StudentNotFoundException;
import com.task.student_management_backend.model.Student;
import com.task.student_management_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setRollNumber(studentDto.getRollNumber());
        student.setGrade(studentDto.getGrade());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }

    public Student updateStudent(Long id, StudentDto studentDto) {
        Student student = getStudentById(id);
        student.setName(studentDto.getName());
        student.setRollNumber(studentDto.getRollNumber());
        student.setGrade(studentDto.getGrade());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}