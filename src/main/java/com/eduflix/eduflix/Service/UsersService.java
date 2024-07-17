package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    public void saveUser(Users user) {
        usersRepository.save(user);
    }
}
