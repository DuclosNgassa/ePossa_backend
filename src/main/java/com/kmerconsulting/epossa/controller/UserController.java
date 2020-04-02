package com.kmerconsulting.epossa.controller;

import com.kmerconsulting.epossa.mapper.UserMapper;
import com.kmerconsulting.epossa.mapper.UserRoleMapper;
import com.kmerconsulting.epossa.model.User;
import com.kmerconsulting.epossa.model.UserDTO;
import com.kmerconsulting.epossa.model.UserPassword;
import com.kmerconsulting.epossa.model.UserRole;
import com.kmerconsulting.epossa.model.UserRoleDTO;
import com.kmerconsulting.epossa.model.UserStatus;
import com.kmerconsulting.epossa.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        user.setCreated_at(LocalDateTime.now());
        user.setRole(UserRole.user);
        User createdUser = userService.save(user);

        if (createdUser == null) {
            return ResponseEntity.notFound().build(); //TODO gibt eine bessere Fehlermeldung aus
        }

        UserDTO createdUserDTO = userMapper.mapToBasisDTO(createdUser);

        return ResponseEntity.ok(createdUserDTO);
    }

    @PutMapping("/changepassword")
    public ResponseEntity<UserDTO> changePassword(@Valid @RequestBody UserPassword userPassword) {

        User changedUser = null;
        if (userService.checkPasswordCorrect(userPassword.getPhone(), userPassword.getOldPassword())) {
            changedUser = userService.changePassword(userPassword.getPhone(), userPassword.getNewPassword());
        }

        if (changedUser == null) {
            return ResponseEntity.notFound().build(); //TODO gibt eine bessere Fehlermeldung aus
        }

        UserDTO changedUserDTO = userMapper.mapToBasisDTO(changedUser);

        return ResponseEntity.ok(changedUserDTO);
    }

    @PutMapping("/role/update/{id}")
    public ResponseEntity<UserDTO> changeRole(@PathVariable(value = "id") Long id, @Valid @RequestBody UserRoleDTO userRoleDTO) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserRole userRole = userRoleMapper.mapDTOToUserRole(userRoleDTO);
        user.setRole(userRole != null ? userRole : user.getRole());

        User updatedUser = userService.update(user);

        UserDTO userDTO = userMapper.mapToBasisDTO(updatedUser);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();

        if (users == null) {
            return ResponseEntity.notFound().build();
        }

        List<UserDTO> userDTOS = users.stream().map(userMapper::mapToBasisDTO).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = userMapper.mapToBasisDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserDTO> getUserByPhone(@PathVariable(value = "phone") String phone) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = userMapper.mapToBasisDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/device/{device}")
    public ResponseEntity<UserDTO> getUserByDevice(@PathVariable(value = "device") String device) {
        User user = userService.findByDevice(device);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = userMapper.mapToBasisDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserDTO>> getAllActiveUsers() {
        List<User> users = userService.findByStatus(UserStatus.active);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }

        List<UserDTO> userDTOS = users.stream().map(userMapper::mapToBasisDTO).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/blocked")
    public ResponseEntity<List<UserDTO>> getAllBlockedUsers() {
        List<User> users = userService.findByStatus(UserStatus.blocked);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }

        List<UserDTO> userDTOS = users.stream().map(userMapper::mapToBasisDTO).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO userDetail) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setName(userDetail.getName() != null ? userDetail.getName() : user.getName());
        user.setEmail(userDetail.getEmail() != null ? userDetail.getEmail() : user.getEmail());
        user.setPhone(userDetail.getPhone() != null ? userDetail.getPhone() : user.getPhone());
        user.setBalance(userDetail.getBalance() != null ? userDetail.getBalance() : user.getBalance());
        user.setRating(userDetail.getRating() != 0 ? userDetail.getRating() : user.getRating());
        user.setStatus(userDetail.getStatus() != null ? userDetail.getStatus() : user.getStatus());

        User updatedUser = userService.update(user);

        UserDTO userDTO = userMapper.mapToBasisDTO(updatedUser);

        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(id);

        UserDTO userDTO = userMapper.mapToBasisDTO(user);

        return ResponseEntity.ok(userDTO);
    }
}
