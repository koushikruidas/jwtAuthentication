package com.test.socialLogin.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProps {

    private List<String> authorizedRedirectUris;

	public List<String> getAuthorizedRedirectUris() {
		return authorizedRedirectUris;
	}

	public void setAuthorizedRedirectUris(List<String> authorizedRedirectUris) {
		this.authorizedRedirectUris = authorizedRedirectUris;
	}
}
