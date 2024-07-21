package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.UserDto;
import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Repository.UsersRepository;
import com.eduflix.eduflix.mappers.UsersMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(usersRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    public HttpEntity<?> saveUser(UserDto userDto) {
        Users user = usersMapper.toEntity(userDto);
        user.setCreatedAt(LocalDate.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users saved = usersRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    public void deleteChosenUser(Long id) {
        usersRepository.deleteById(id);
    }

    public HttpEntity<?> updateUser(Long id, UserDto userDto) {
        Users users = usersMapper.toEntity(userDto);
        users.setId(id);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users saved = usersRepository.save(users);
        return ResponseEntity.ok(saved);
    }
}
