package com.Student.Student.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SchoolDto {
    private Long id;
    private String name;
    private String adress;
}
