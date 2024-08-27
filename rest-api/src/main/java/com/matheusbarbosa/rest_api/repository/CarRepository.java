package com.matheusbarbosa.rest_api.repository;


import com.matheusbarbosa.rest_api.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByNameStartingWith(String name);

}
