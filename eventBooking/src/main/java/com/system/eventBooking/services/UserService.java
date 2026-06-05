package com.system.eventBooking.services;

import com.system.eventBooking.enums.Role;
import com.system.eventBooking.entities.UserEntity;
import com.system.eventBooking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public UserEntity getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no user with such id"));
    }

    public void deleteUser(Long id){
        UserEntity user = getUserById(id);
        userRepository.delete(user);
    }

    public void setRole(Long id, Role role){
        UserEntity user = getUserById(id);
        user.setRole(role);
        userRepository.save(user);
    }
}
