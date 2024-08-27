package com.matheusbarbosa.rest_api;

import com.matheusbarbosa.rest_api.domain.Car;
import com.matheusbarbosa.rest_api.domain.Category;
import com.matheusbarbosa.rest_api.domain.Status;

public class CarFactory {

    public static Car createCar(){
        Car car= new Car();
        car.setId(1L);
        car.setName("Versa");
        car.setCategory(Category.PROGRAMMING);
        car.setStatus(Status.NOT_STARTED);
        return car;
    }

}

