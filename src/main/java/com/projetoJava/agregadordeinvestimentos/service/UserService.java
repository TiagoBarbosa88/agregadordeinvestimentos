package com.projetoJava.agregadordeinvestimentos.service;

import com.projetoJava.agregadordeinvestimentos.controller.CreateUserDto;
import com.projetoJava.agregadordeinvestimentos.controller.UpdateUserDto;
import com.projetoJava.agregadordeinvestimentos.entity.User;
import com.projetoJava.agregadordeinvestimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUSer(CreateUserDto createUserDto) {

        // DTO -> Entity
        var entity = new User(
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto){
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if(userEntity.isPresent()){
            var user = userEntity.get();
            if(updateUserDto.username() != null){
                user.setUsername(updateUserDto.username());
            }
            if(updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }
    }

    public void deleteById(String userId){
       var id = UUID.fromString(userId);

       var userExists = userRepository.existsById(id);

       if(userExists){
           userRepository.deleteById(id);
       }
    }
}
