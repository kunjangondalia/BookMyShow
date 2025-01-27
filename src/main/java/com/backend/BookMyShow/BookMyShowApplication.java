package com.backend.BookMyShow;

import com.backend.BookMyShow.controllers.UserController;
import com.backend.BookMyShow.dtos.SignUpRequestDTO;
import com.backend.BookMyShow.dtos.SignUpResponseDTO;
import com.backend.BookMyShow.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;
	public static void main(String[] args) {

		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDTO requestDTO = new SignUpRequestDTO();
		requestDTO.setFirstName("John");
		requestDTO.setLastName("Smith");
		requestDTO.setEmail("johnsmith@gmail.com");
		requestDTO.setPassword("password");

		SignUpResponseDTO responseDTO =  userController.signUp(requestDTO);
		System.out.println(responseDTO.getResponseStatus());
	}
}
