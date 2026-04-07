package com.example.hotelbooking.Repository;

import com.example.hotelbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}