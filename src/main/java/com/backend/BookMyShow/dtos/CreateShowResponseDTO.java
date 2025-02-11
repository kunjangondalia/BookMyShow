package com.backend.BookMyShow.dtos;

import com.backend.BookMyShow.models.Show;
import lombok.Data;

@Data
public class CreateShowResponseDTO {
    private ResponseStatus responseStatus;
    private Show show;
}
