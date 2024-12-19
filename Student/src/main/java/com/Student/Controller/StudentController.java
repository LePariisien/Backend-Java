package com.Student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Student.Dto.StudentWithSchoolDto;
import com.Student.Entity.Student;
import com.Student.Service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    // @Operation(summary = "Créer un étudiant")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    // @Operation(summary = "Récupérer la liste des étudiants")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    // @Operation(summary = "Récupérer un étudiant par son ID")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    // @Operation(summary = "Mettre à jour un étudiant")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return ResponseEntity.ok(studentService.updateStudent(id, updatedStudent));
    }

    @DeleteMapping("/{id}")
    // @Operation(summary = "Supprimer un étudiant")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/school")
    public ResponseEntity<StudentWithSchoolDto> getStudentByIdWithSchool(@PathVariable Long id) {
        StudentWithSchoolDto studentWithSchoolDto = studentService.findByIdWithSchool(id);
        if (studentWithSchoolDto != null) {
            return ResponseEntity.ok(studentWithSchoolDto);
        }
        return ResponseEntity.notFound().build();
    }
}
