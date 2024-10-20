package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.StudentDto;
import com.eduflix.eduflix.Dto.TeacherDto;
import com.eduflix.eduflix.Dto.UserDto;
import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Enum.UserRole;
import com.eduflix.eduflix.Repository.UsersRepository;
import com.eduflix.eduflix.controller.GeneratePassAndUsername;
import com.eduflix.eduflix.mappers.UsersMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(usersRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }
    @Transactional
    public HttpEntity<?> saveUser(UserDto userDto) {
        if (usersRepository.existsByUsername(userDto.username)){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("already exists!");
        }
        Users user = usersMapper.toEntity(userDto);
        user.setCreatedAt(LocalDate.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users saved = usersRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    public HttpEntity<?> deleteChosenUser(Long id) {
        usersRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("successful deleted");
    }

    public HttpEntity<?> updateUser(Long id, UserDto userDto) {
        Users users = usersMapper.toEntity(userDto);
        users.setId(id);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
        return ResponseEntity.status(200).body("User successful updated");
    }

    public GeneratePassAndUsername saveUser2(StudentDto student) {
        Users user = new Users();
        user.setGender(student.getGender());
        user.setRole(UserRole.ROLE_STUDENT);
        return getGeneratePassAndUsername(user);
    }

    public GeneratePassAndUsername saveUser2(TeacherDto teacher) {
        Users user = new Users();
        user.setGender(teacher.getGender());
        user.setRole(UserRole.ROLE_TEACHER);
        // i would implement a method that only save a user to db
        // but in the next stages random username and password might be needed
        // So i decided to choose this approach(using getGeneratePassAndUsername() for both student and teacher saving )
        return getGeneratePassAndUsername(user);
    }

    private GeneratePassAndUsername getGeneratePassAndUsername(Users user) {
        String generatedPassword = generatePassword();
        String generatedUsername = generateUsername();
        user.setUsername(generatedUsername);
        user.setPassword(passwordEncoder.encode(generatedPassword));
        user.setEnabled(true);
        user.setLocked(false);
        user.setCreatedAt(LocalDate.now());
        usersRepository.save(user);
        return new GeneratePassAndUsername(generatedPassword, generatedUsername, user);
    }

    public String generateUsername() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public Optional<Users> findById(Long userId) {
        return usersRepository.findById(userId);
    }

    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
