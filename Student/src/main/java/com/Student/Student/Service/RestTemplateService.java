package com.Student.Student.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Student.Student.Dto.SchoolDto;

@Service
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    public SchoolDto getSchool(long id) {
        String url = "http://SCHOOL/schools/" + id;
        return restTemplate.getForObject(url, SchoolDto.class);
    }

}
