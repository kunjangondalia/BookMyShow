package com.backend.BookMyShow.exceptions;


public class UnAuthorizedAccessException extends Exception{
    public UnAuthorizedAccessException(String message){
        super(message);
    }
}
