package com.kmerconsulting.epossa.mapper;

import com.kmerconsulting.epossa.model.User;
import com.kmerconsulting.epossa.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper<User, UserDTO> {

    @Override
    public User mapToBasisEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setCreated_at(userDTO.getCreated_at());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setDevice(userDTO.getDevice());
        user.setStatus(userDTO.getStatus());
        user.setRole(userDTO.getRole());
        user.setBalance(userDTO.getBalance());
        user.setRating(userDTO.getRating());

        return user;
    }

    @Override
    public UserDTO mapToBasisDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setCreated_at(user.getCreated_at());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setDevice(user.getDevice());
        userDTO.setStatus(user.getStatus());
        userDTO.setRole(user.getRole());
        userDTO.setBalance(user.getBalance());
        userDTO.setRating(user.getRating());

        return userDTO;
    }
}
