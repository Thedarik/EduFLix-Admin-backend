package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.UserDto;
import com.eduflix.eduflix.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    @GetMapping
    public HttpEntity<?> getAllUsers(){
        return usersService.getAll();
    }

    @PostMapping
    public HttpEntity<?> saveNewUser(@RequestBody UserDto userDto) {
        return usersService.saveUser(userDto);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        usersService.deleteChosenUser(id);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        return usersService.updateUser(id, userDto);
    }
}
