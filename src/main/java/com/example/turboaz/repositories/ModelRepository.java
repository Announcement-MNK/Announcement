package com.example.turboaz.repositories;

import com.example.turboaz.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> getAllByMakeId(long makeId);
}
