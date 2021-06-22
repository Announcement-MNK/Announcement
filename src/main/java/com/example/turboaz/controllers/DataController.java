package com.example.turboaz.controllers;

import com.example.turboaz.dtos.MakeDto;
import com.example.turboaz.dtos.ModelDto;
import com.example.turboaz.dtos.UserDto;
import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.FuelType;
import com.example.turboaz.exceptions.AmountGreaterThanZeroException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.models.City;
import com.example.turboaz.models.Make;
import com.example.turboaz.models.Model;
import com.example.turboaz.services.SearchCollectionService;
import com.google.api.client.util.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DataController {
    SearchCollectionService searchCollectionService;

    public DataController(SearchCollectionService searchCollectionService) {
        this.searchCollectionService = searchCollectionService;
    }

    @GetMapping("/data/makes/{id}/models")
    public ResponseEntity<List<ModelDto>> getModelsByMakeId(@PathVariable long id) {
        return new ResponseEntity<>(searchCollectionService.getModelsByMakeId(id), HttpStatus.OK);
    }

    @GetMapping("/data/makes")
    public ResponseEntity<List<MakeDto>> getAllMakes() {
        return new ResponseEntity<>(Lists.newArrayList(searchCollectionService.getMakes()), HttpStatus.OK);
    }
    @GetMapping("/data/fuelTypes")
    public ResponseEntity<List<FuelType>> getFuelTypes(){
        return new ResponseEntity<>(searchCollectionService.getFuelTypes(),HttpStatus.OK);
    }
    @GetMapping("/data/bodyTypes")
    public ResponseEntity<List<BodyType>> getBodyTypes(){
        return new ResponseEntity<>(searchCollectionService.getBodyTypes(),HttpStatus.OK);
    }
    @GetMapping("/data/cities")
    public ResponseEntity<List<City>> getCities(){
        return new ResponseEntity<>(searchCollectionService.getCities(),HttpStatus.OK);
    }
    @GetMapping("/profile/wallet")
    public ResponseEntity<Double> getWallet(@RequestAttribute("user")UserDto dto){
        return new ResponseEntity<>(searchCollectionService.getWallet(dto.getUsername()),HttpStatus.OK);
    }
    @PostMapping("/profile/wallet/increase")
    public  ResponseEntity<Double> increaseWallet(@RequestAttribute("user") UserDto userDto,@RequestBody double amount) throws AmountGreaterThanZeroException, UserNotFoundException {
        double a=searchCollectionService.increaseWallet(userDto.getUsername(),amount);
        return new ResponseEntity<>(searchCollectionService.increaseWallet(userDto.getUsername(),amount),HttpStatus.OK);
    }
}
