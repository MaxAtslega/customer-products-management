package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user based on their email address.
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}