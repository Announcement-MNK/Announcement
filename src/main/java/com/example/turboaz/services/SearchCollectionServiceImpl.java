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
import com.example.turboaz.models.User;
import com.example.turboaz.repositories.CityRepository;
import com.example.turboaz.repositories.MakeRepository;
import com.example.turboaz.repositories.ModelRepository;
import com.example.turboaz.repositories.UserRepository;
import com.google.api.client.util.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
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
    public List<ModelDto> getModelsByMakeId(Long makeId) {
        List<ModelDto> modelDtos=new ArrayList<>();
        modelRepository.getAllByMakeId(makeId).forEach(m->modelDtos.add(new ModelDto(m)));
        return modelDtos;
    }

    @Override
    public List<MakeDto> getMakes() {
        List<MakeDto> makeDtos=new ArrayList<>();
        makeRepository.findAll().forEach(m->makeDtos.add(new MakeDto(m)));
        return Lists.newArrayList(makeDtos);
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
    public double increaseWallet(String username, double amount) throws AmountGreaterThanZeroException, UserNotFoundException {
        if (amount>0){
            User user = userRepository.findUserByUsername(username);
            if(user==null) throw new UserNotFoundException("This user not found");
            user.setBalance(user.getBalance()+amount);
            return user.getBalance();
        }else{
            throw new AmountGreaterThanZeroException("Amount must greater than 0");
        }

    }
}
