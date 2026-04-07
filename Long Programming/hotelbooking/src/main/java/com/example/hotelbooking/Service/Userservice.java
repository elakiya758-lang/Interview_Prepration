package com.example.hotelbooking.Service;

import com.example.hotelbooking.Repository.UserRepo;
import com.example.hotelbooking.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userservice {

    private final UserRepo userRepo;

    public Userservice(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}