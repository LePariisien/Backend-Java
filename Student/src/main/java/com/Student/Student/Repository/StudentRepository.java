package com.Student.Student.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.Student.Student.Entity.Student;

public interface StudentRepository extends MongoRepository<Student, Long> {
    List<Student> findBySchoolId(String schoolId);

    
}
