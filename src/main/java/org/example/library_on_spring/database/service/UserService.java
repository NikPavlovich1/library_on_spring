package org.example.library_on_spring.database.service;

import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}


