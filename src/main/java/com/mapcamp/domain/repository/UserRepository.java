package com.mapcamp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcamp.domain.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	User findByEmail(String email);

	void save(org.springframework.boot.autoconfigure.security.SecurityProperties.User user);
}