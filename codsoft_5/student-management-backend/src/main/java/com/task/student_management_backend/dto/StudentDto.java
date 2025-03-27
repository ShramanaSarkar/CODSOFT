package com.task.student_management_backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String name;
    private String rollNumber;
    private String grade;
}