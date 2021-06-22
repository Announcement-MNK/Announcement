package com.example.turboaz.services;

import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.FuelType;
import com.example.turboaz.models.City;
import com.example.turboaz.models.Make;
import com.example.turboaz.models.Model;
import com.example.turboaz.models.User;
import com.example.turboaz.repositories.CityRepository;
import com.example.turboaz.repositories.MakeRepository;
import com.example.turboaz.repositories.ModelRepository;
import com.example.turboaz.repositories.UserRepository;
import com.google.api.client.util.Lists;

import java.util.Arrays;
import java.util.List;

public class SearchCollectionServiceImpl implements SearchCollectionService {
    MakeRepository makeRepository;
    ModelRepository modelRepository;
    CityRepository cityRepository;
    UserRepository userRepository;

    public SearchCollectionServiceImpl(MakeRepository makeRepository, ModelRepository modelRepository,
                                       CityRepository cityRepository, UserRepository userRepository) {
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Model> getModelsByMakeId(Long makeId) {

        return modelRepository.getAllByMakeId(makeId);
    }

    @Override
    public List<Make> getMakes() {
        return Lists.newArrayList(makeRepository.findAll());
    }

    @Override
    public List<FuelType> getFuelTypes() {
        return Arrays.asList(FuelType.values());
    }

    @Override
    public List<BodyType> getBodyTypes() {
        return Arrays.asList(BodyType.values());
    }

    @Override
    public List<City> getCities() {
        return Lists.newArrayList(cityRepository.findAll());
    }

    @Override
    public double getWallet(String username) {
        User user = userRepository.findUserByUsername(username);
        return user.getBalance();
    }

    @Override
    public double increaseWallet(String username, double amount) {
        User user = userRepository.findUserByUsername(username);
        user.setBalance(amount);
        return user.getBalance();
    }
}
