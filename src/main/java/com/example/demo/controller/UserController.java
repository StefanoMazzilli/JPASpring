package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	//elenco gli endpoint principali con le operazioni CRUD
	
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping
	public User createNewUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}
	
	@PutMapping("{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		user.setName(userDetails.getName());
		user.setLastName(userDetails.getLastName());
		user.setAge(userDetails.getAge());
		return userRepository.save(user);
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
		return "User deleted";
	}
	
	//adesso uso i metodi custom
	
	@GetMapping("/searchByName")
	public List<User> getUsersByName(@RequestParam String name) {
		return userRepository.findByName(name);
	}
	
	@GetMapping("/searchByNameAndLastname")
	public List<User> getUsersByNameAndLastname(@RequestParam String name, @RequestParam String lastName) {
		return userRepository.findByNameAndLastname(name, lastName);
	}
	
	@GetMapping("/searchByAgeLessThan")
	public List<User> getUsersByAgeLessThan(@RequestParam int age) {
		return userRepository.findByAgeLessThan(age);
	}
	
	@GetMapping("/searchMario")
	public List<User> getMario(String name) {
		name = "Mario";
		return userRepository.findByName(name);
	}
	
	@GetMapping("/searchMinors")
	public List<User> getMinors(int age) {
		age = 18;
		return userRepository.findByAgeLessThan(age);
	}
	
	@GetMapping("/searcMarioRossi")
	public List<User> getMarioRossi(String name, String lastName) {
		name = "Mario";
		lastName = "Rossi";
		return userRepository.findByNameAndLastname(name, lastName);
	}
}
