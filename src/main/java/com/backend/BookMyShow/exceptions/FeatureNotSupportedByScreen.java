package com.backend.BookMyShow.exceptions;

public class FeatureNotSupportedByScreen extends Exception{
    public FeatureNotSupportedByScreen(String message){
        super(message);
    }
}