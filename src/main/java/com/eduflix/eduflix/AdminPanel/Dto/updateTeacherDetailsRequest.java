package com.eduflix.eduflix.AdminPanel.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class updateTeacherDetailsRequest {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String contact;
}
