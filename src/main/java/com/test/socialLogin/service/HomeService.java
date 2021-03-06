package com.test.socialLogin.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.socialLogin.entity.User;
import com.test.socialLogin.repository.UserRepository;

@Service
public class HomeService implements IHomeService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}
	
}
