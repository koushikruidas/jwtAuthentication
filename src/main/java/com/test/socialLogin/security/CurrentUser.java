package com.test.socialLogin.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/*
 * The @CurrentUser annotation itself does not get the current user details.
 * It is simply a marker annotation that is used to tell Spring Security to inject the current user details into a parameter that is annotated with @CurrentUser.
 * The actual injection of the current user details is performed by Spring Security's @AuthenticationPrincipal annotation. 
 * When you annotate a method parameter with @CurrentUser, it is equivalent to annotating it with @AuthenticationPrincipal(expression="user").
 * The @AuthenticationPrincipal annotation is a Spring Security annotation that resolves the currently authenticated principal object, 
 * which is usually a UserDetails object. The expression="user" attribute tells Spring Security to resolve the principal object as a UserDetails object.
 * 
 * When you annotate a method parameter with @AuthenticationPrincipal(expression="user"), 
 * Spring Security will automatically inject the currently authenticated UserDetails object into that parameter. 
 * The UserDetails object contains information about the currently authenticated user, such as their username, 
 * password (which is usually encrypted), authorities, and any other custom user information you may have added.
 * 
 * In your case, you have created a custom annotation @CurrentUser which extends @AuthenticationPrincipal. 
 * So, when you annotate the currentUser parameter in the getCurrentUser() method with @CurrentUser, 
 * Spring Security will use your custom annotation instead of the default @AuthenticationPrincipal annotation to inject the 
 * currently authenticated UserDetails object into that parameter.
 * 
 * This makes it easier to inject the current user details into your controller methods without having to explicitly use @AuthenticationPrincipal.
 * 
 */
@Target({ElementType.PARAMETER, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal 
public @interface CurrentUser {

}
