package com.test.socialLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc // for swagger implementation we have to use this annotation
public class SocialLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialLoginApplication.class, args);
	}

}
