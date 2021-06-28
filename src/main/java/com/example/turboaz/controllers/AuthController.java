package com.example.turboaz.controllers;

import com.example.turboaz.dtos.LoginPostDto;
import com.example.turboaz.dtos.LoginResponseDto;
import com.example.turboaz.dtos.RegisterPostDto;
import com.example.turboaz.dtos.RegisterResponseDto;
import com.example.turboaz.models.ConfirmationToken;
import com.example.turboaz.models.User;
import com.example.turboaz.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LoginResponseDto> signIn(@RequestBody LoginPostDto loginDto) {
        return new ResponseEntity<>(service.login(loginDto), HttpStatus.OK);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmUserAccount(@RequestParam("token")String confirmationToken){
        service.confirmAccount(confirmationToken);
        return new ResponseEntity(HttpStatus.OK);
    }
}
