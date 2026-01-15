package com.brown.asrs_user_authentication_api.controller;


import com.brown.asrs_user_authentication_api.dto.user.UserRegistrationDto;
import com.brown.asrs_user_authentication_api.service.UserService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public String registerUser(UserRegistrationDto registrationDto) {
        System.out.println("Received User Registration Request for: " + registrationDto.getFullName());
        return "User registered successfully";
    }
    @GetMapping("/api/users")
    public String getAllUsers() {
        System.out.println("Received request to get all users");
        return "List of all users";
    }

    @PutMapping("/api/users/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserRegistrationDto registrationDto) {
        System.out.println("Received request to update user with id: " + id);
        return "User with id " + id + " has been updated.";

    }
    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable @NotNull Long id, String email) {
        System.out.println("Received request to delete user with id: " + id);
        System.out.println("Received request to delete user with email: " + email);
        return "User with id " + id + " has been deleted.";
    }

}
