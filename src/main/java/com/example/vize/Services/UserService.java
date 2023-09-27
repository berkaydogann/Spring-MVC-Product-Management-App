package com.example.vize.Services;


import com.example.vize.Entities.User;
import com.example.vize.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public User userLogin(User user){
        Optional<User> u = userRepository.findByEmailEqualsAndPasswordEquals(user.getEmail(),user.getPassword());
        try{
            if(u.isPresent()){
                return u.get();
            }

        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
