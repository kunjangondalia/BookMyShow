package com.backend.BookMyShow.controllers;


import com.backend.BookMyShow.dtos.CreateShowRequestDTO;
import com.backend.BookMyShow.dtos.CreateShowResponseDTO;
import com.backend.BookMyShow.dtos.ResponseStatus;
import com.backend.BookMyShow.models.Show;
import com.backend.BookMyShow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.backend.BookMyShow.exceptions.*;

@Controller
public class ShowController {

    private ShowService showService;
    @Autowired
    ShowController(ShowService showService){
        this.showService = showService;
    }

    public CreateShowResponseDTO createShow(CreateShowRequestDTO requestDTO) {
        CreateShowResponseDTO responseDTO = new CreateShowResponseDTO();
        try{
            Show createdShow = showService.createShow(
                    requestDTO.getUserId(),
                    requestDTO.getMovieId(),
                    requestDTO.getScreenId(),
                    requestDTO.getStartTime(),
                    requestDTO.getEndTime(),
                    requestDTO.getPricingConfig(),
                    requestDTO.getFeatures()
            );
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setShow(createdShow);
        }
        catch (MovieNotFoundException | ScreenNotFoundException | FeatureNotSupportedByScreen | InvalidDateException | UserNotFoundException | UnAuthorizedAccessException ex){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
