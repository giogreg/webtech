package com.webtech.demo.controller;

import com.webtech.demo.model.Users;
import com.webtech.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class UsersController{

    // Create a Logger
    Logger logger = Logger.getLogger(UrlsController.class.getName());

    @Autowired
    private UsersService usersService;

    @PostMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Users> registerNewUser(@RequestBody Users newUser){
        Users resUser = usersService.registerNewUser(newUser);
        logger.info(resUser.toString());
        return new ResponseEntity<Users>(resUser, HttpStatus.CREATED);
    }
}
