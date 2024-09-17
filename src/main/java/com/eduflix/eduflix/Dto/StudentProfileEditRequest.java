package com.eduflix.eduflix.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileEditRequest {
    private String email;
    private String phone;
    private String parentContact;
    private String username;
}
