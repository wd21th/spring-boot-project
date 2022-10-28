package com.example.mdbspringboot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.model.User;

public interface ItemRepository extends MongoRepository<User, String> {
	
	@Query("{email:'?0'}")
	User findUserByEmail(String email);
	
	@Query(value="{phone:'?0'}", fields="{'email' : 1, 'quantity' : 1}")
	List<User> findAll(String category);
	
	public long count();

}
