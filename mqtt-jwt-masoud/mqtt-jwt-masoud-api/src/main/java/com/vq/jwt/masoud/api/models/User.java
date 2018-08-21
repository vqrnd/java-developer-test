package com.vq.jwt.masoud.api.models;

import javax.validation.constraints.*;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class is a DataModel for MongoDB users table
 */
@Document(collection = "users")
public class User {

	@Id
	private String id;

	@Email
	@Indexed
	private String email;

	private String password;

	
	
	
	public User() {
	}

	@Override
	public String toString() {
		return String.format("User[id=%s, email='%s']", id, email);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
