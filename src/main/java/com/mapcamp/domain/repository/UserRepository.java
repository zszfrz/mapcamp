package com.mapcamp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapcamp.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

}
