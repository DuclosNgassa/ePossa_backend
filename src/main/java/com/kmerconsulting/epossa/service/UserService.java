package com.kmerconsulting.epossa.service;

import com.kmerconsulting.epossa.model.User;
import com.kmerconsulting.epossa.model.UserStatus;
import com.kmerconsulting.epossa.repository.UserRepository;
import com.kmerconsulting.epossa.security.SecurityConfiguration;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    SecurityConfiguration securityConfiguration;
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        user.setPassword(securityConfiguration.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone).orElse(null);
    }

    public User findByDevice(String device) {
        return userRepository.findByDevice(device).orElse(null);
    }

    public List<User> findByStatus(UserStatus status) {
        return userRepository.findByStatus(status).orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name).orElse(Collections.emptyList());
    }

    public boolean checkPasswordCorrect(String phone, String oldPassword) {
        User user = findByPhone(phone);
        if (user != null) {
            if (securityConfiguration.passwordEncoder().matches(oldPassword, user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User changePassword(String phone, String newPassword) {
        User user = findByPhone(phone);
        if (user != null) {
            user.setPassword(securityConfiguration.passwordEncoder().encode(newPassword));
            return update(user);
        }
        return null;
    }

}
