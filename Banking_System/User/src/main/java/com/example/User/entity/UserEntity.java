package com.example.User.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_entity")
public class UserEntity implements UserDetails {
	// Unique identifier for the user

	@Id
	@jakarta.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// First name of the user
	private String firstName;

	// Last name of the user
	private String lastName;

	// Email address of the user (unique)
	@Column(unique = true, nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	// Password of the user
	private String password;

	/**
	 * Returns the authorities granted to the user.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	/**
	 * Returns the username used to authenticate the user.
	 */
	@Override
	public String getUsername() {

		return null;
	}

	/**
	 * Indicates whether the user's account has expired.
	 */
	@Override
	public boolean isAccountNonExpired() {

		return false;
	}

	/**
	 * Indicates whether the user is locked or unlocked.
	 */
	@Override
	public boolean isAccountNonLocked() {

		return false;
	}

	/**
	 * Indicates whether the user's credentials (password) has expired.
	 */
	@Override
	public boolean isCredentialsNonExpired() {

		return false;
	}

	/**
	 * Indicates whether the user is enabled or disabled.
	 */
	@Override
	public boolean isEnabled() {

		return false;
	}

}
