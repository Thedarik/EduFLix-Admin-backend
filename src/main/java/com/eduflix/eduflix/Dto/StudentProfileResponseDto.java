package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Enum.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String parentContact;
    private Gender gender;
    private double payStatus;
    private String imageUrl;

    public StudentProfileResponseDto(Long id,
                                     String firstname,
                                     String lastname,
                                     String email,
                                     String phone,
                                     String parentContact,
                                     Gender gender,
                                     double payStatus,
                                     String imageUrl) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.parentContact = parentContact;
        this.gender = gender;
        this.payStatus = payStatus;
        this.imageUrl = imageUrl;
    }
}
