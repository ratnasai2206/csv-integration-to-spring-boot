package com.csvfiles.csvfileintegration.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csvfiles.csvfileintegration.entity.User;
import com.csvfiles.csvfileintegration.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository repository;
	
	public List<User> saveAllUser(List<User> users){
		return repository.saveAll(users);
	}
}
