package com.java.weatherAlert.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.weatherAlert.model.User;
import com.java.weatherAlert.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(AlertPreferenceService.class);
	
	@Autowired
	UserRepository userRepository;
	
	
	@Cacheable("UserCache")
	public User createUser(User user) {
		log.info("Create User Initiated");
		Optional optional = Optional.of(user);
		if(optional.isPresent()) {
			userRepository.save(user);
		}
		
		return user;
	}
	
	@Cacheable("UserCache")
	public User getById(int userId) {
		log.info("Get User byId Initiated");
		User user = userRepository.getById(userId);
		Optional optional = Optional.of(user);
		if(optional.isPresent()) {
			return user;
		}
		return null;
	}
	
	@Cacheable("UserCache")
	public User getByName(String username) {
		log.info("Get User byUserName Initiated");
		User user = userRepository.findByUserName(username);
		Optional optional = Optional.of(user);
		if(optional.isPresent()) {
			return user;
		}
		return null;
	}
	
	public List<User> getAllUserS() {
		log.info("Get All Users Initiated");
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	} 
	
}
