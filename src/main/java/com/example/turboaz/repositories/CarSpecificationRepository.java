package com.example.turboaz.repositories;

import com.example.turboaz.models.CarSpecification;
import org.springframework.data.repository.CrudRepository;

public interface CarSpecificationRepository extends CrudRepository<CarSpecification, Long> {
    CarSpecification getCarSpecificationById(Long id);
}
