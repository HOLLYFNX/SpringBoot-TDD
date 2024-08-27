package com.matheusbarbosa.rest_api;

import com.matheusbarbosa.rest_api.domain.Car;
import com.matheusbarbosa.rest_api.domain.Category;
import com.matheusbarbosa.rest_api.domain.Status;
import com.matheusbarbosa.rest_api.exception.CarNotFoundException;
import com.matheusbarbosa.rest_api.repository.CarRepository;
import com.matheusbarbosa.rest_api.service.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.matheusbarbosa.rest_api.CarFactory.createCar;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    @DisplayName("Success - Should save car with success!")
    void shouldSaveCarWithSuccess(){
        when(carRepository.save(ArgumentMatchers.any(Car.class)))
                .thenReturn(createCar());
        Car created = carService.createCar(createCar());
        assertThat(created.getName()).isSameAs(createCar().getName());
        assertNotNull(created.getId());
        assertEquals(created.getId(), 1);
    }

@Test
@DisplayName("Success - should return the list of cars with success!")
    void shouldReturnListOfCarsWithSuccess(){
     when(carRepository.findAll()).thenReturn(List.of(createCar()));
     List<Car> cars= carService.ListAllCars();
     assertEquals(1, cars.size());
}


@Test
@DisplayName("Success - should find a car by id with success!")
    void shouldFindCarByIdWithSuccess() throws CarNotFoundException
{
    Car car = new Car();
    car.setId(1L);
    car.setName("Versa");
    car.setCategory(Category.PROGRAMMING);
    car.setStatus(Status.NOT_STARTED);

    when(carRepository.findById(car.getId())).thenReturn(Optional.of(createCar()));
    Optional<Car> expected = carService.getCarById(car.getId());
    assertThat(expected.get().getId()).isEqualTo(car.getId());
    assertThat(expected.get().getName()).isEqualTo(car.getName());
    assertThat(expected.get().getCategory()).isEqualTo(car.getCategory());
    assertThat(expected.get().getStatus()).isEqualTo(car.getStatus());
    assertDoesNotThrow(() -> {
    carService.getCarById(car.getId());
    });
}

    @Test
    @DisplayName("Error - should throw exception when try to find a car by id ")
    void ShouldThrowExceptionWhenTryToFindABook() throws CarNotFoundException {
        Car car = new Car();
        car.setId(1L);
        car.setName("Versa");
        car.setStatus(Status.NOT_STARTED);
        car.setCategory(Category.PROGRAMMING);


        when(carRepository.findById(200L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CarNotFoundException.class,
                () -> carService.getCarById( 200L));
        assertEquals("Car with id " +  200L + "not found", exception.getMessage());
    }

    @Test
    @DisplayName("Success - should delete car with success ")
    void shouldDeleteCarWithSuccess() {
        when(carRepository.findById(createCar().getId())).thenReturn(Optional.empty());

        ResponseEntity<Object> expected = carService.deleteCarById(createCar().getId());
        assertThat(expected.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);


    }

    @Test
    @DisplayName("Success - should find a book by id with success")
    void ShouldFindBookByPartialName() {
        Car car = new Car();
        car.setId(1L);
        car.setName("corsa");
        car.setStatus(Status.IN_PROGRESS);
        car.setCategory(Category.SOFT_SKILLS);


        when(carRepository.findByNameStartingWith("cor")).thenReturn((List.of(car)));
        List<Car> expected = carService.listCarsThatStartsWithPartialName("cor");
        assertThat(expected.get(0).getId()).isEqualTo(car.getId());
        assertThat(expected.get(0).getName()).isEqualTo(car.getName());
        assertThat(expected.get(0).getCategory()).isEqualTo(car.getCategory());
        assertThat(expected.get(0).getStatus()).isEqualTo(car.getStatus());
        assertThat(expected.get(0).getName()).isNotEqualTo(createCar().getName());



    }


}
