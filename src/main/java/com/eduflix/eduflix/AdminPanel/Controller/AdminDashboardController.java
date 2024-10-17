package com.eduflix.eduflix.AdminPanel.Controller;

import com.eduflix.eduflix.AdminPanel.Dto.DashboardStatistic;
import com.eduflix.eduflix.AdminPanel.Dto.SubscriptionStatistics;
import com.eduflix.eduflix.AdminPanel.Service.AdminDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/all-course")
    public ResponseEntity<?> allCourse() {
        return ResponseEntity.ok(adminDashboardService.getAllCourseList());
    }

    // filter courses in admin panel by title
    @GetMapping("/allCourse/filter")
    public ResponseEntity<?> allCourseFilter(@RequestParam String title) {
        return ResponseEntity.ok(adminDashboardService.filterCoursesByTitle(title));
    }

    @GetMapping("/total-signing")
    public ResponseEntity<DashboardStatistic> totalSigning() {
        return ResponseEntity.ok(adminDashboardService.totalSigning());
    }

    @GetMapping("/monthly-signing")
    public ResponseEntity<DashboardStatistic> monthlySigning() {
        return ResponseEntity.ok(adminDashboardService.monthlySigning());
    }

    @GetMapping("/total-course")
    public ResponseEntity<DashboardStatistic> totalCourse() {
        return ResponseEntity.ok(adminDashboardService.totalCourse());
    }

    //todo: implement an API to get all monthly paid students number

    @GetMapping("/total-class")
    public ResponseEntity<DashboardStatistic> totalClass() {
        return ResponseEntity.ok(adminDashboardService.totalClass());
    }

    @GetMapping("/mon-subsc")
    public ResponseEntity<List<SubscriptionStatistics>> monSubsc(@RequestParam("year") int year) {
        return ResponseEntity.ok(adminDashboardService.getMonthlySubscriptions(year));
    }
}
