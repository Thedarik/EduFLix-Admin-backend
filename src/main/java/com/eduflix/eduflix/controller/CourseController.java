package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.CourseDto;
import com.eduflix.eduflix.Dto.CourseWithIdDto;
import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Service.CourseService;
import com.eduflix.eduflix.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;

    @PostMapping("/add")
    public HttpEntity<?> addCourse(@RequestBody CourseDto courseDto,
                                   @RequestParam("image") MultipartFile image) {
        try {
            Course savedCourse = courseService.saveNewCourse(courseDto, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the course");
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CourseDto>> filter(@RequestParam String title) {
        if (courseService.exists(title)) {
            return ResponseEntity.ok(courseService.findByFilter(title));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/my-courses")
    public ResponseEntity<Set<CourseDto>> getMyCourses(@RequestParam("studentId") Long studentId) {
        if (studentService.exists(studentId)) {
            return ResponseEntity.ok(studentService.findByStudentId(studentId));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> takeCourseWithClasses(@PathVariable Long id) {
        if (courseService.exists(id)) {
            return ResponseEntity.ok(courseService.getCourseWithClasses(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No elements in database");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCourseForUpdate(@PathVariable Long id) {
        if (courseService.exists(id)) {
            return ResponseEntity.ok(courseService.getCourseForUpdate(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No elements in database");
        }
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> updateCourseFields(@PathVariable Long id,
                                            @RequestBody CourseWithIdDto courseWithIdDto) {
        if (courseService.exists(id)) {
            courseService.updateCourse(courseWithIdDto, id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("successfully updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/image/{courseId}")
    public ResponseEntity<byte[]> getCourseImage(@PathVariable Long courseId) {
        try {
            Course course = courseService.getCourseById(courseId);
            byte[] imageData = course.getImage().getData();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
