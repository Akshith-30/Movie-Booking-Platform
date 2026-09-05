package com.Akshith.movie_reservation_system.exceptions;

public class SeatUnavailableException extends RuntimeException{
    public SeatUnavailableException(String message){
        super(message);
    }
}
