package com.matheusbarbosa.rest_api.service;

import com.matheusbarbosa.rest_api.domain.Car;
import com.matheusbarbosa.rest_api.exception.CarNotFoundException;
import com.matheusbarbosa.rest_api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> ListAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) throws CarNotFoundException {
        if (carRepository.findById(id).isEmpty()){
            throw  new CarNotFoundException(id);
        }
        return carRepository.findById(id);
    }

    public ResponseEntity<Car> updateCarById(Car car, Long id) {
        return carRepository.findById(id).map(carToUpdate -> {
            carToUpdate.setName(car.getName());
            carToUpdate.setCategory(car.getCategory());
            carToUpdate.setStatus(car.getStatus());
            Car updated = carRepository.save(carToUpdate);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<Object> deleteCarById(Long id) {
        return carRepository.findById(id).map(carToDelete -> {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public List<Car> listCarsThatStartsWithPartialName(String partialName) {
        return carRepository.findByNameStartingWith(partialName);
    }


}
