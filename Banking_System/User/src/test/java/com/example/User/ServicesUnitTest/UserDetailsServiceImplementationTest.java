package com.example.User.ServicesUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.User.Repository.UserRepository;
import com.example.User.entity.UserEntity;
import com.example.User.service.UserDetailsServiceImplementation;

@SpringBootTest
public class UserDetailsServiceImplementationTest {
	@Autowired
	private UserDetailsServiceImplementation userDetailsServiceImplementation;
	@MockBean
	private UserRepository userRepository;
	private UserEntity user;
	@BeforeEach
	void setup() {
		user = UserEntity.builder().email("test@example.com")
				.password("123")
				.build();
	}

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Mock repository method to return user
        Mockito.when(userRepository.findUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Call the service method
        UserDetails userDetails = userDetailsServiceImplementation.loadUserByUsername("test@example.com");

        // Assertions
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("123", userDetails.getPassword());
        // Add more assertions if UserDetails implementation has additional fields or logic
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Mock repository method to return empty optional
       Mockito.when(userRepository.findUserByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Call the service method and assert that it throws UsernameNotFoundException
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsServiceImplementation.loadUserByUsername("nonexistent@example.com");
        });
    }
}
