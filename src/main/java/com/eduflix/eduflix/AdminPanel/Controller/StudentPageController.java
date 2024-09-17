package com.eduflix.eduflix.AdminPanel.Controller;

import com.eduflix.eduflix.Dto.StudentDto;
import com.eduflix.eduflix.Dto.StudentProfileResponseDto;
import com.eduflix.eduflix.Entity.Student;
import com.eduflix.eduflix.Exceptions.NotFoundException;
import com.eduflix.eduflix.Service.StudentService;
import com.eduflix.eduflix.Service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentPageController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestBody StudentDto student) {

        if (studentService.existsByEmail(student.getEmail())) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("already exists!");
        } else
            return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentProfileResponseDto>> studentList() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(studentService.findById(id));
    }
}
