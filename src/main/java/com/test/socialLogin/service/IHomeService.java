package com.test.socialLogin.service;

import java.util.List;
import java.util.Optional;

import com.test.socialLogin.entity.User;

public interface IHomeService {
	public List<User> getUsers();
	public Optional<User> findById(Long id);
}
