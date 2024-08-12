//package com.eduflix.eduflix.AdminPanel.Controller;
//
//import com.eduflix.eduflix.AdminPanel.Dto.AdminRegisterDto;
//import com.eduflix.eduflix.AdminPanel.Service.AdminService;
//import com.eduflix.eduflix.Exceptions.AlreadyUserExistsException;
//import com.google.api.gax.rpc.AlreadyExistsException;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/admin")
//@AllArgsConstructor
//public class AdminRegistrationController {
//
//    private final AdminService adminService;
//
//    @PostMapping("/create")
//    public ResponseEntity<AdminRegisterDto>createAdmin(@RequestBody AdminRegisterDto dto) throws AlreadyUserExistsException {
//        if (adminService.exists(dto.getEmail())){
//            throw new AlreadyUserExistsException("User already exists");
//        }
//        else
//            return ResponseEntity.ok(adminService.addAdmin(dto));
//    }
//}