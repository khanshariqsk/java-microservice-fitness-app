package com.fitness.userservice.repository;

import com.fitness.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Boolean existsByKeycloakId(String keycloakId);

    Optional<User> findByKeycloakId(String keycloakId);
}