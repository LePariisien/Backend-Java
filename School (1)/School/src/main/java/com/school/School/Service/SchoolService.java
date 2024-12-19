package com.school.School.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.School.Entity.SchoolEntity;
import com.school.School.Repository.SchoolRepository;

@Service

public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public List<SchoolEntity> getAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<SchoolEntity> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public SchoolEntity createSchool(SchoolEntity school) {
        return schoolRepository.save(school);
    }

    public SchoolEntity updateSchool(Long id, SchoolEntity schoolDetails) {
        return schoolRepository.findById(id).map(school -> {
            school.setName(schoolDetails.getName());
            school.setAddress(schoolDetails.getAddress());
            school.setPrincipal(schoolDetails.getPrincipal());
            return schoolRepository.save(school);
        }).orElseThrow(() -> new RuntimeException("School not found"));
    }
    
    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}

