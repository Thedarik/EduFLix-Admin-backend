package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.SupportMessageDto;
import com.eduflix.eduflix.Service.StudentService;
import com.eduflix.eduflix.Service.SupportMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/support")
@AllArgsConstructor
public class SupportMessageController {

    private final SupportMessageService supportMessageService;
    private final StudentService studentService;

    @GetMapping("/from-student")
    public ResponseEntity<Object> sendToAdmin(@RequestBody SupportMessageDto message) {
        if (studentService.exists(message.getId())) {
            //todo: change sendMessageFromStudent() with websocket in js code
            supportMessageService.sendMessageFromStudent(message.getId(), message);
            return ResponseEntity.ok("Successfully sent message");
        } else
            return ResponseEntity.notFound().build();
    }
}
