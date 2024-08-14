package com.eduflix.eduflix.AdminPanel.Service;

import com.eduflix.eduflix.AdminPanel.Dto.AdminRegisterDto;
import com.eduflix.eduflix.Entity.Admin;
import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Repository.AdminRepository;
import com.eduflix.eduflix.Repository.UsersRepository;
import com.eduflix.eduflix.Service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final UsersService usersService;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean exists(String email) {
        return adminRepository.existsByEmail(email);
    }

    public Admin addAdmin(AdminRegisterDto dto) {
        Users user = new Users();
        String password = usersService.generatePassword();
        user.setUsername(dto.getUsername());
        user.setEnabled(true);
        user.setLocked(false);
        user.setCreatedAt(LocalDate.now());
        user.setPassword(passwordEncoder.encode(password));
        usersRepository.save(user);
        Admin admin = new Admin();
        admin.setEmail(dto.getEmail());
        admin.setFirstname(dto.getFirstname());
        admin.setLastname(dto.getLastname());
        admin.setContact(dto.getContact());
        admin.setUsers(user);
        adminRepository.save(admin);
        return admin;
    }

}
