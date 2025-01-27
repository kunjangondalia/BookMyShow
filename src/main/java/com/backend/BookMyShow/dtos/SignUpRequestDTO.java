package com.backend.BookMyShow.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String FirstName;
    private String LastName;
    private String email;
    private String password;
}
