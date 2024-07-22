package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Entity.Users;
import lombok.Getter;
import lombok.Setter;
@Getter
public class GeneratePassAndUsername {
    @Setter
    private String password;
    @Setter
    private String username;
    private Users users;

    public GeneratePassAndUsername(String password, String username, Users users) {
        this.password = password;
        this.username = username;
        this.users = users;
    }
}
