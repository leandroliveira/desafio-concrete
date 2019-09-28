package com.app.desafioconcrete.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.desafioconcrete.api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findById(UUID id);
	User findByUserToken(UUID userToken);
	User findByEmail(String email); 
}
