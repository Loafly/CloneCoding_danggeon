package com.clone_coding.danggeon.repository;

import com.clone_coding.danggeon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    User findByUsername(String username);

    User findByEmail(String email);
}
