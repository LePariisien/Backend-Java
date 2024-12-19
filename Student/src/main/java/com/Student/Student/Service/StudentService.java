package com.Student.Student.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Student.Student.Dto.SchoolDto;
import com.Student.Student.Dto.StudentWithSchoolDto;
import com.Student.Student.Entity.Student;
import com.Student.Student.Repository.StudentRepository;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplateService restTemplateService;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setAge(updatedStudent.getAge());
                    student.setEmail(updatedStudent.getEmail());
                    student.setSchoolId(updatedStudent.getSchoolId());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsBySchoolId(String schoolId) {
        return studentRepository.findBySchoolId(schoolId);
    }

    public StudentWithSchoolDto findByIdWithSchool(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            return null;
        }

        SchoolDto schoolDto = restTemplateService.getSchool(student.get().getSchoolId());

        return new StudentWithSchoolDto(student.get(), schoolDto);
    }
    
}
