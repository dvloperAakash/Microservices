package com.microservices.hms.services;

import java.util.List;


import com.microservices.hms.entities.User;

public interface UserService {
	//user operation
	
	//create
	User saveUser(User user);
	
	//get all user
	List<User>getAllUser();
	
	//get single user of given userId
	
	User getUser(String userId);
}
