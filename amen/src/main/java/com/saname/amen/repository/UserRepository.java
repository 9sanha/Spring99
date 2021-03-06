package com.saname.amen.repository;

import com.saname.amen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findBySnsId(Long snsId);

    Optional<User> findByEmail(String snsEmail);
}
