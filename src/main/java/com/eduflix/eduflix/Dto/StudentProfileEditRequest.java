package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Enum.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileEditRequest {
    private String email;
    private String phone;
    private String parentContact;
    private String username;
    private Gender gender;
}
