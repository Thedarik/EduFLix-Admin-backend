//package com.eduflix.eduflix.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api")
//public class NotificationController {
//    @Autowired
//    private NotificationService notificationService;
//
//    @PostMapping("/notify")
//    public void notifyUsers(@RequestBody NotificationRequest request) throws IOException {
//        notificationService.sendNotification(request.getRole(), request.getMessage());
//    }
//}