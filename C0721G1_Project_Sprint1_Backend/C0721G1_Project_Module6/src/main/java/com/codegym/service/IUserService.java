package com.codegym.service;

import com.codegym.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGenericService<User> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    List<Long> findRoleByUserId(Long id);

//    void updatePassword(Long id, String password);
}
