package com.Student.Dto;

import com.Student.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithSchoolDto {
    private Student student;
    private SchoolDto school;
}
