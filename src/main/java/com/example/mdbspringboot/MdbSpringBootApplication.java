package com.example.mdbspringboot;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mdbspringboot.model.User;
import com.example.mdbspringboot.repository.CustomItemRepository;
import com.example.mdbspringboot.repository.ItemRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner{
	
	@Autowired
	ItemRepository UserRepo;
	
	@Autowired
	CustomItemRepository customRepo;
	
	List<User> itemList = new ArrayList<User>();

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
	
	public void run(String... args) {
		System.out.println("-------------CREATE Users -------------------------------\n");
		UserRepo.deleteAll();


		createUsers();


		getUserByEmail("wprogrammist@gmail.com");


		updatePhone("wprogrammist@gmail.com", "8 707 650 8413");




		showAllUsers();


		deleteUser("4");


		findCountOfUsers();
	}
	
	// CRUD operations

	//CREATE
	void createUsers() {
		System.out.println("Data creation started...");

		UserRepo.save(new User("1", "wprogrammist@gmail.com", "+7 707 219 6303"));
		UserRepo.save(new User("2", "happy.century.02@bk.ru", "+7 747 150 9057"));
		UserRepo.save(new User("3", "chan@gmail.com", "+7 747 295 2751"));
		UserRepo.save(new User("4", "pater@gmail.com", "+7 747 356 0535"));
		UserRepo.save(new User("5", "bonny@gmail.com", "+7 747 357 5270"));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllUsers() {
		 
		 itemList = UserRepo.findAll();
		 
		 itemList.forEach(item -> System.out.println(getUserDetails(item)));
	 }
	 
	 // 2. Get item by email
	 public void getUserByEmail(String name) {
		 System.out.println("Getting item by email: " + name);
		 User item = UserRepo.findUserByEmail(name);
		 System.out.println(getUserDetails(item));
	 }
	 

	 
	 // 4. Get count of documents in the collection
	 public void findCountOfUsers() {
		 long count = UserRepo.count();
		 System.out.println("Number of documents in the collection = " + count);
	 }

	 // UPDATE APPROACH 2: Using MongoTemplate
	 public void updatePhone(String email, String phone) {
		 System.out.println("Updating for " + email);
		 customRepo.updatePhone(email, phone);
	 }
	 
	 // DELETE
	 public void deleteUser(String id) {
		 UserRepo.deleteById(id);
		 System.out.println("Item with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getUserDetails(User item) {

		 System.out.println(
		 "Item Email: " + item.getEmail() +
		 ", \nItem Phone: " + item.getPhone()
		 );
		 
		 return "";
	 }
}

