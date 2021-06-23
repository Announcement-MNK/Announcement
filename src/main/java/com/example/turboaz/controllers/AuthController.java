package com.example.turboaz.controllers;

import com.example.turboaz.dtos.LoginPostDto;
import com.example.turboaz.dtos.LoginResponseDto;
import com.example.turboaz.dtos.RegisterPostDto;
import com.example.turboaz.dtos.RegisterResponseDto;
import com.example.turboaz.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RequestMapping(value = "/api/v1/users")
@RestController
public class AuthController {

    UserService service;

    public AuthController(UserService service){
        this.service = service;
    }


    @PostMapping(path = "/create")
    public ResponseEntity<RegisterResponseDto> createUser(@RequestBody RegisterPostDto userDTO) {
        return new ResponseEntity<>(service.register(userDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<LoginResponseDto> signin(@RequestBody LoginPostDto loginDto) {
        return new ResponseEntity<>(service.login(loginDto), HttpStatus.OK);
    }
}
