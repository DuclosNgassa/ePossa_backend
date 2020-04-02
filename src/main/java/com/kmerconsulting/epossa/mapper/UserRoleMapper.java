package com.kmerconsulting.epossa.mapper;

import com.kmerconsulting.epossa.model.UserRole;
import com.kmerconsulting.epossa.model.UserRoleDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public UserRoleDTO mapUserRoleToDTO(UserRole userRole){
        switch (userRole){
            case admin:
                return buildUserRoleDTO("admin");
            case user:
            default:
                return buildUserRoleDTO("user");
        }
    }

    public UserRole mapDTOToUserRole(UserRoleDTO userRoleDTO){
        switch (userRoleDTO.getRole()){
            case "admin":
                return UserRole.admin;
            case "user":
            default:
                return UserRole.user;
        }
    }

    UserRoleDTO buildUserRoleDTO(String role){
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRole(role);
        return userRoleDTO;
    }
}
