package com.example.vize.Repositories;


import com.example.vize.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailEqualsAndPasswordEquals(String email, String password);


}