package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RegistrationDetails;
import com.example.demo.repository.RegistrationDetailsRepository;

@Service
public class RegistrationDetailsService {
	
	@Autowired
	private RegistrationDetailsRepository repo;
	
	
	public String saveRegistrationDetails(RegistrationDetails detail) {
		repo.save(detail);
		return "Registered successfully";
	}
	
	
	public List<RegistrationDetails> getRegistrationDetails() {
		return repo.findAll();
	}
	
	public Optional<RegistrationDetails> getRegistrationDetailsById(int id) {
		return repo.findById(id);
	}
	
	public List<RegistrationDetails> getRegistrationDetailsByEmail(String email) {
		return repo.findByEmail(email);
	}


	public List<RegistrationDetails> getRegistrationDetailsByRole(String role) {
		// TODO Auto-generated method stub
		return repo.findByRole(role);
	}
	
}
