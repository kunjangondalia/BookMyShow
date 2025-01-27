package com.backend.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    private int userId;
    private ResponseStatus responseStatus;
}
