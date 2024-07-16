package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//trovare tutti gli utenti per nome
	List<User> findByName(String name);
	
	//trovare tutti gli utenti con età inferiore a un numero
	List<User> findByAgeLessThan(int age);
	
	//trovare tutti gli utenti per nome e cognome
	List<User> findByNameAndLastname(String name, String lastName);
}
