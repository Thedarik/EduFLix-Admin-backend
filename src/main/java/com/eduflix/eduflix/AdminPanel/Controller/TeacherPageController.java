package com.eduflix.eduflix.AdminPanel.Controller;

import com.eduflix.eduflix.AdminPanel.Dto.updateTeacherDetailsRequest;
import com.eduflix.eduflix.Dto.TeacherDto;
import com.eduflix.eduflix.Entity.Teacher;
import com.eduflix.eduflix.Exceptions.AlreadyUserExistsException;
import com.eduflix.eduflix.Exceptions.NotFoundException;
import com.eduflix.eduflix.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@AllArgsConstructor
public class TeacherPageController {

    private final TeacherService teacherService;

    @GetMapping("/list")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid TeacherDto teacher) throws AlreadyUserExistsException {
        if (teacherService.existsByEmailOrContact(teacher)) {
            throw new AlreadyUserExistsException("A teacher with this email or contact already exists");
        }
        return ResponseEntity.ok(teacherService.createTeacher(teacher));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long id) throws NotFoundException {
            return ResponseEntity.ok(teacherService.deleteTeacher(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody updateTeacherDetailsRequest request)
            throws com.eduflix.eduflix.Exceptions.NotFoundException {
            return ResponseEntity.ok(teacherService.updateTeacherDetails(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countTeachers() {
        return ResponseEntity.ok(teacherService.countTeacher());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(teacherService.findById(id));
    }
}
