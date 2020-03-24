package com.kmerconsulting.epossa.repository;

import com.kmerconsulting.epossa.model.User;
import com.kmerconsulting.epossa.model.UserStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<List<User>> findByName(String name);

    Optional<User> findByPhone(String phone);

    Optional<User> findByDevice(String device);

    Optional<List<User>> findByStatus(UserStatus status);
}
