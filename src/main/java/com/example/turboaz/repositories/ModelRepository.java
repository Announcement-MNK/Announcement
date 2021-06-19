package com.example.turboaz.repositories;

import com.example.turboaz.models.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, Long> {
}
