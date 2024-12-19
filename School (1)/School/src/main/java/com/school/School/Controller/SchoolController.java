package com.school.School.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.school.School.Entity.SchoolEntity;
import com.school.School.Service.SchoolService;

@RestController
@RequestMapping("/schools")
// @Tag(name = "School", description = "Endpoints pour gérer les écoles")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    // @Operation(summary = "Obtenir toute les écoles")
    public List<SchoolEntity> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    // @Operation(summary = "Obtenir un école par ID")
    public ResponseEntity<SchoolEntity> getSchoolById(@PathVariable Long id) {
        return schoolService.getSchoolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    // @Operation(summary = "Créer une école")
    public SchoolEntity createSchool(@RequestBody SchoolEntity SchoolEntity) {
        return schoolService.createSchool(SchoolEntity);
    }

    @PutMapping("/{id}")
    // @Operation(summary = "Mettre à jour une école par ID")
    public ResponseEntity<SchoolEntity> updateSchool(@PathVariable Long id, @RequestBody SchoolEntity schoolDetails) {
            return ResponseEntity.ok(schoolService.updateSchool(id, schoolDetails));
    }

    @DeleteMapping("/{id}")
    // @Operation(summary = "Supprimer une école par ID")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}



