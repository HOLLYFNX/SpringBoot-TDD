package com.matheusbarbosa.rest_api.controller;

import com.matheusbarbosa.rest_api.domain.Car;
import com.matheusbarbosa.rest_api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listAllCars() {
        return carService.ListAllCars();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Car> getCarById(@PathVariable(value = "id") Long id) throws Exception {
        return carService.getCarById(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car> updateCarById(@PathVariable(value = "id") Long id, @RequestBody Car car) {
        return carService.updateCarById(car, id);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteCarById(@PathVariable(value = "id") Long id) {
        return carService.deleteCarById(id);

    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listCarsThatStartsWithPartialName
            (@RequestParam String name) {
        return carService.listCarsThatStartsWithPartialName(name);

    }

}