package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.StudentProfileEditRequest;
import com.eduflix.eduflix.Dto.StudentProfileResponseDto;
import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Service.StudentService;
import com.eduflix.eduflix.Service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class StudentProfileController {

    private final StudentService studentService;
    private final UsersService usersService;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentProfileResponseDto> getStudentProfile() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return ResponseEntity.ok(studentService.getStudentProfileByUsername(username));
    }

    @GetMapping("/image/{userId}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable("userId") Long userId) {
        Optional<Users> user = usersService.findById(userId);
        if (user.isPresent() && user.get().getImage() != null) {
            byte[] image = user.get().getImage().getData();
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
                    .body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/edit/{studentId}")
    public ResponseEntity<Object> editProfile(@PathVariable("studentId") Long studentId,
                                              @RequestParam StudentProfileEditRequest request) {
        if (studentService.exists(studentId)) {
            return ResponseEntity.ok(studentService.editStudentProfile(studentId, request));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }
}
