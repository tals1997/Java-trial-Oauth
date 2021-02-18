package com.developervisits.oauth2.repository;

import com.developervisits.oauth2.entity.NormalUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<NormalUser, String> {

	NormalUser findByUsername(String username);
	NormalUser findByEmail(String email);
	
}
