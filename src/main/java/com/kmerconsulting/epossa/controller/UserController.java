package com.kmerconsulting.epossa.controller;

import com.kmerconsulting.epossa.dao.UserService;
import com.kmerconsulting.epossa.model.User;
import com.kmerconsulting.epossa.model.UserStatus;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        user.setCreated_at(LocalDateTime.now());
        return userService.save(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/phone/{phone}")
    public ResponseEntity<User> getUserByPhone(@PathVariable(value = "phone") String phone) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/active")
    public List<User> getAllActiveUsers() {
        return userService.findByStatus(UserStatus.active);
    }

    @GetMapping("/users/blocked")
    public List<User> getAllBlockedUsers() {
        return userService.findByStatus(UserStatus.blocked);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetail) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setName(userDetail.getName());
        user.setPhone(userDetail.getPhone());
        user.setBalance(userDetail.getBalance());
        user.setRating(userDetail.getRating());
        user.setPassword(userDetail.getPassword());
        user.setStatus(userDetail.getStatus());

        User updatedUser = userService.save(user);

        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(id);

        return ResponseEntity.ok().build();
    }

}
