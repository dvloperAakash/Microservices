package com.microservices.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.hms.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
