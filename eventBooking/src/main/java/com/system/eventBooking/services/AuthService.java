package com.system.eventBooking.services;

import com.system.eventBooking.entities.UserEntity;
import com.system.eventBooking.enums.Role;
import com.system.eventBooking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String register(String email, String password){
        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("Email already taken");
        }
        UserEntity user = new UserEntity();
        user.setEmail(email);
        //hash the password and save hashed version
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);

        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(String email, String password){
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with this email does not exist")
        );
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Wrong password!");
        }
        return jwtService.generateToken(user);
    }


}
