package com.example.turboaz.services;

import com.example.turboaz.dtos.MakeDto;
import com.example.turboaz.dtos.ModelDto;
import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.FuelType;
import com.example.turboaz.exceptions.AmountGreaterThanZeroException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.models.City;
import com.example.turboaz.models.Make;
import com.example.turboaz.models.Model;

import java.util.List;

/**
 * Get all the data for search
 */
public interface SearchCollectionService {
    /** gets models by make id
     * @param makeId
     * @return List<Model>
     */
    List<ModelDto> getModelsByMakeId(Long makeId);

    /** gets all makes
     * @return List<Make>
     */

    Iterable<MakeDto> getMakes();

    /** gets all fuel types
     * @return List<FuelType>
     */
    List<FuelType> getFuelTypes();

    /** gets all body types
     * @return List<BodyType>
     */
    List<BodyType> getBodyTypes();

    /** gets all cities
     * @return List<City>
     */
    List<City> getCities();

    /** gets user wallet
     * @param username
     * @return double
     */
    double getWallet(String username);

    /** increases amount of user wallet
     * @param username
     * @param amount
     * @return double
     */
    double increaseWallet(String username, double amount) throws AmountGreaterThanZeroException, UserNotFoundException;

}
