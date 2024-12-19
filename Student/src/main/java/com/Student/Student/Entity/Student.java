package com.Student.Student.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter


public class Student {
    @Id
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private Long schoolId;
}
