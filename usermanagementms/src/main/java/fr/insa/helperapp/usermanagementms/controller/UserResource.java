package fr.insa.helperapp.usermanagementms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fr.insa.helperapp.usermanagementms.model.User;
import fr.insa.helperapp.usermanagementms.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @PostMapping
    public String createUser(@RequestBody User newUser) {
        int rowsAffected = userRepository.createUser(newUser);
        return rowsAffected > 0 ? "User created successfully" : "Error creating user";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        updatedUser.setId(id);
        int rowsAffected = userRepository.updateUser(updatedUser);
        return rowsAffected > 0 ? "User updated successfully" : "Error updating user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        int rowsAffected = userRepository.deleteUser(id);
        return rowsAffected > 0 ? "User deleted successfully" : "Error deleting user";
    }
}

