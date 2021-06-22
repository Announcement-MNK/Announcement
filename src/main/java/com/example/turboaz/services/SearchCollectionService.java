package com.example.turboaz.services;

import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.FuelType;
import com.example.turboaz.models.City;
import com.example.turboaz.models.Make;
import com.example.turboaz.models.Model;

import java.util.List;

/**
 * Get all the data for search
 */
public interface SearchCollectionService {
    /**
     * @param makeId
     * @return List<Model>
     */
    List<Model> getModelsByMakeId(Long makeId);

    /**
     * @return List<Make>
     */

    Iterable<Make> getMakes();

    /**
     * @return List<FuelType>
     */
    List<FuelType> getFuelTypes();

    /**
     * @return List<BodyType>
     */
    List<BodyType> getBodyTypes();

    /**
     * @return List<City>
     */
    List<City> getCities();

    /**
     * @param username
     * @return double
     */
    double getWallet(String username);

    /**
     * @param username
     * @param amount
     * @return double
     */
    double increaseWallet(String username, double amount);

}