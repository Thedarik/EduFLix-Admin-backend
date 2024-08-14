package com.eduflix.eduflix.AdminPanel.Controller;

import com.eduflix.eduflix.AdminPanel.Service.AdminDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/all-course")
    public ResponseEntity<?> allCourse() {
        return ResponseEntity.ok(adminDashboardService.getAllCourseList());
    }
}
