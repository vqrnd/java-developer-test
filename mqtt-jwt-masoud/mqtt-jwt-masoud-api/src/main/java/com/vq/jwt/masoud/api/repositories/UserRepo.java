package com.vq.jwt.masoud.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.vq.jwt.masoud.api.models.User;

/**
 * This is a interface for a Spring generated implementation of MongoDB users table repository
 */
public interface UserRepo extends PagingAndSortingRepository<User, String> {

	public User findByEmail(String email);
}
