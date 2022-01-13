package com.codegym.service;

import com.codegym.model.Role;

import java.util.Optional;

public interface IRoleService extends IGenericService<Role> {
    Optional<Role> findByName(String name);
}
