package com.test.socialLogin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.test.socialLogin.entity.User;
import com.test.socialLogin.exception.ResourceNotFoundException;
import com.test.socialLogin.model.response.UserIdentityAvailability;
import com.test.socialLogin.model.response.UserProfile;
import com.test.socialLogin.model.response.UserSummary;
import com.test.socialLogin.repository.UserRepository;
import com.test.socialLogin.security.CurrentUser;
import com.test.socialLogin.security.UserPrincipal;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@ApiIgnore @CurrentUser UserPrincipal currentUser) {
    	/*
    	 * @CurrentUser annotation used to invoke the current user
    	 * @ApiIgnore is a swagger annotation used to tell swagger that we do not want UserPrincipal to be shown in the swagger input list for this api
    	 */
    	
        UserSummary userSummary = 
        		new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getEmail());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());

        return userProfile;
    }

}
