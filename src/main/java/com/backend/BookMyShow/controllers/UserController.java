package com.backend.BookMyShow.controllers;

import com.backend.BookMyShow.dtos.ResponseStatus;
import com.backend.BookMyShow.dtos.SignUpRequestDTO;
import com.backend.BookMyShow.dtos.SignUpResponseDTO;
import com.backend.BookMyShow.models.User;
import com.backend.BookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    public SignUpResponseDTO signUp(SignUpRequestDTO requestDTO){
        SignUpResponseDTO responseDTO = new SignUpResponseDTO();
        try{
            User user = userService.signUp(
                    requestDTO.getFirstName(),
                    requestDTO.getLastName(),
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );
            responseDTO.setUserId(user.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);

        }catch(Exception e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
