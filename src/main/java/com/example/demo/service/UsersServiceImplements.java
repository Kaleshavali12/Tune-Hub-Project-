package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
@Service
public class UsersServiceImplements implements UsersService{
	@Autowired
     UsersRepository repo;
	@Override
	public String addUsers(Users user) {
		repo.save(user);
		return "User add successfully";
	}
	@Override
	public boolean emailExists(String email) {
		
		if(repo.findByEmail(email)==null) {        //findByEmail doesnot contains in repository we will give code in UsersRepository
			                                       //findById contain in repository we will not give code. we can repicate this for findByEmail method.
		return false;
	}
	else{
		return true;
	}
	
	

}
	@Override
	public boolean validateUser(String email, String password) {
           Users user=repo.findByEmail(email);
           
           String db_pass=user.getPassword();
           if(password.equals(db_pass)) {
        	   return true;
           }
           else {
		return false;
           }
           }
	
	@Override
	public String getRole(String email) {
		Users user=repo.findByEmail(email);
		return user.getRole();
	}
	
	@Override
	public Users getUser(String email) {
		return repo.findByEmail(email);
	}
	@Override
	public void updateUser(Users user) {
		repo.save(user);
		
	}
	
	
           
	}
	
	

