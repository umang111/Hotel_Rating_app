package com.userService.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userService.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
