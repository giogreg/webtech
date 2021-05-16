package com.webtech.demo.service;

import com.webtech.demo.model.Users;
import com.webtech.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users registerNewUser(Users newUser){
        usersRepository.save(newUser);
        return newUser;
    }
}
