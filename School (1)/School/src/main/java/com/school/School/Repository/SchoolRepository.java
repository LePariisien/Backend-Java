package com.school.School.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.School.Entity.SchoolEntity;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
}