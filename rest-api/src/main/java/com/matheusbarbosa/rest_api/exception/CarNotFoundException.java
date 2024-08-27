package com.matheusbarbosa.rest_api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,
        reason = "Car not found in database")
public class CarNotFoundException extends Exception {

    public CarNotFoundException(Long id){
        super("Car with id " +  id + "not found");
    }


}