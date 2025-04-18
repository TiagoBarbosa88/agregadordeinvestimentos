package com.projetoJava.agregadordeinvestimentos.service;

import com.projetoJava.agregadordeinvestimentos.controller.CreateUserDto;
import com.projetoJava.agregadordeinvestimentos.entity.User;
import com.projetoJava.agregadordeinvestimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUSer(CreateUserDto createUserDto){

        // DTO -> Entity
       var entity =  new User(
               null,
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null );

       var userSaved = userRepository.save(entity);

       return userSaved.getUserId();
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
