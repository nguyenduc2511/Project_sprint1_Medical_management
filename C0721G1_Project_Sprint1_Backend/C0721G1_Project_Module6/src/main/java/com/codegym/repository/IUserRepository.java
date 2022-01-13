package com.codegym.repository;

import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM  c0721g1_project_sprint1_backend.user_role WHERE user_id=:id", nativeQuery = true)
    List<Long> findRoleByUserId(@Param("id") Long id);

//    @Query(value = "UPDATE c0721g1_project_sprint1_backend.user SET password=:password WHERE id=:id", nativeQuery = true)
//    void updatePassword(@Param("id") Long id, @Param("password") String password);
}
