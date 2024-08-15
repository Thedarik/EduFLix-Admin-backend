package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.CourseGetDTO;
import com.eduflix.eduflix.Dto.MasterDTO;
import com.eduflix.eduflix.Service.HomeService;
import com.eduflix.eduflix.util.RestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestConstants.PATH + "/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/masters")
    HttpEntity<List<MasterDTO>> mastersList() {
        return homeService.getMastersList();
    }

    @GetMapping("/courses")
    HttpEntity<List<CourseGetDTO>> homePageCourses() {
        return homeService.getCoursesList();
    }

}
