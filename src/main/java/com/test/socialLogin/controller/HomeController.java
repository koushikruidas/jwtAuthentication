package com.test.socialLogin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.socialLogin.entity.User;
import com.test.socialLogin.service.IHomeService;

@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private IHomeService homeService;
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(homeService.getUsers(), HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/getById/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		return homeService.findById(id);
	}
	
	@GetMapping("/index")
	public String index() {
		return "Welcome to the index page";
	}
}
