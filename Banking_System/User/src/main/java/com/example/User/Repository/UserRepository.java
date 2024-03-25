package com.example.User.Repository;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.User.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{

	Optional<UserEntity> findUserByEmail(String email);

//	User findUserByEmail(String email);

	void deleteByEmail(String email);

	
	
	

	

}
