package com.csvfiles.csvfileintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csvfiles.csvfileintegration.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
