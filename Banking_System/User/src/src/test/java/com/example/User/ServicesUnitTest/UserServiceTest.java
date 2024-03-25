package com.example.User.ServicesUnitTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.User.Repository.UserRepository;
import com.example.User.entity.UserEntity;
import com.example.User.service.UserService;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private ModelMapper modelMapper;
	private UserEntity userEntity;
	
	@BeforeEach
	void setUp() {
		userEntity = UserEntity.builder()
				.id(1)
				.firstName("Smith")
				.lastName("Adam")
				.email("smith@gmail.com")
				.password("123")
				.build();
	}
	@Test
	public void testFetchUserByEmail() {
		Mockito.when(userRepository.findUserByEmail("smith@gmail.com"))
		.thenReturn(Optional.of(userEntity));
		ResponseEntity<Optional<UserEntity>> responseEntity = (ResponseEntity<Optional<UserEntity>>) userService.fetchUserByEmail("smith@gmail.com");
		assertEquals(200,responseEntity.getStatusCodeValue());
		assertEquals(userEntity, responseEntity.getBody().get());
	}


	  @Test
	    public void testDeleteUserDetails_UserExists() {
	        // Mock userRepository behavior
	       Mockito. when(userRepository.findUserByEmail(userEntity.getEmail())).thenReturn(Optional.of(userEntity));

	        // Invoke the deleteUserDetails method
	        assertDoesNotThrow(() -> userService.deleteUserDetails(userEntity.getEmail()));

	        // Verify that userRepository.deleteByEmail() is called with the correct email
	        verify(userRepository).deleteByEmail(userEntity.getEmail());
	    }




}
