package com.kmerconsulting.epossa.dao;

import com.kmerconsulting.epossa.model.User;
import com.kmerconsulting.epossa.model.UserStatus;
import com.kmerconsulting.epossa.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User findByPhone(String phone){
        return userRepository.findByPhone(phone).orElse(null);
    }

    public List<User> findByStatus(UserStatus status){
        return userRepository.findByStatus(status).orElse(null);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
