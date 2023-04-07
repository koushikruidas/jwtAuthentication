package com.test.socialLogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.test.socialLogin.security.JwtAuthenticationEntryPoint;
import com.test.socialLogin.security.JwtAuthenticationFilter;
import com.test.socialLogin.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(  // The prePostEnabled property enables Spring Security pre/post annotations.
							  // The securedEnabled property determines if the @Secured annotation should be enabled.
							  // The jsr250Enabled property allows us to use the @RoleAllowed annotation.
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
// for details visit: https://www.javainuse.com/spring/boot-jwt

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    /*
     * As AuthenticationManagerBuilder needs an UserDetails as input, (line no. 63)
     * this CustomUserDetailsService implements UserDetailsService, which have an abstract method loadUserByUsername() which return an UserDetails.
     * Hence, we override the method loadUserByUsername() from UserDetailsService interface.
     * 
     * This loadUserByUsername returns a UserDetails(which is a spring security interface) by fetching User data from database
     * and using that User data to create a UserDetails.
     * 
     * To do so, we created a class called UserPrinciple which implements UserDetails interface,
     * 
     * This UserPrinciple class has a method called create(User user), takes User as input
     * and returns a UserDetails object.
     *  
     */

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/home/index",
                        "/swagger-ui/",
                        "/v2/api-docs",
				        "/webjars/**")
                        .permitAll()
                    .antMatchers("/security/api/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}