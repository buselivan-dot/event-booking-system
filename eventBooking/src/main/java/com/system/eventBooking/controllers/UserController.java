package com.system.eventBooking.controllers;

import com.system.eventBooking.entities.UserEntity;
import com.system.eventBooking.enums.Role;
import com.system.eventBooking.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/role")
    public ResponseEntity<Void> setRole(@PathVariable Long id, @RequestParam Role role){
        userService.setRole(id, role);
        return ResponseEntity.noContent().build();
    }



}
