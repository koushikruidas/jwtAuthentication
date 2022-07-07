package com.test.socialLogin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.socialLogin.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	ArrayList<User> findAllUsers();
//	@Query("Select u from User u where u.firstName = :name")
//	public List<User> findByName(@Param("name") String name);
	
	Optional<User> findByEmail(String email);

    Optional<User> findByNameOrEmail(String name, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByName(String name);
}
