package com.example.hotelbooking.Controller;

import com.example.hotelbooking.Service.Userservice;
import com.example.hotelbooking.entity.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Usercontroller {

    private final Userservice userServices;

    public Usercontroller(Userservice userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/postuser")
    public User createUser(@RequestBody User user) {
        return userServices.saveUser(user);
    }

    @GetMapping("/getuser")
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }
}