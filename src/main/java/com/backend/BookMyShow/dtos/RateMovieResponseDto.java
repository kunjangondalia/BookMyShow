package com.backend.BookMyShow.dtos;

import com.backend.BookMyShow.models.*;

import lombok.Data;

@Data
public class RateMovieResponseDto {
    private ResponseStatus responseStatus;
    private Rating rating;
}
