package com.backend.BookMyShow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.backend.BookMyShow.dtos.GetAverageMovieRequestDto;
import com.backend.BookMyShow.dtos.GetAverageMovieResponseDto;
import com.backend.BookMyShow.dtos.RateMovieRequestDto;
import com.backend.BookMyShow.dtos.RateMovieResponseDto;
import com.backend.BookMyShow.dtos.ResponseStatus;
import com.backend.BookMyShow.models.Rating;
import com.backend.BookMyShow.services.RatingsService;

@Controller
public class RatingsController {
    RatingsService ratingService;

    @Autowired
    public RatingsController(RatingsService ratingService){
        this.ratingService = ratingService;
    }

    public RateMovieResponseDto rateMovie(RateMovieRequestDto requestDto){
        RateMovieResponseDto responseDto = new RateMovieResponseDto();
        try{
            Rating rating = ratingService.rateMovie(requestDto.getUserId(), requestDto.getMovieId(), requestDto.getRating());
            responseDto.setRating(rating);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public GetAverageMovieResponseDto getAverageMovieRating(GetAverageMovieRequestDto requestDto){
        GetAverageMovieResponseDto responseDto = new GetAverageMovieResponseDto();
        try{
            Double rating = ratingService.getAverageRating(requestDto.getMovieId());
            responseDto.setAverageRating(rating);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
