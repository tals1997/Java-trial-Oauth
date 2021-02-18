package com.developervisits.oauth2.repository;

import com.developervisits.oauth2.entity.Oauth2User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Oauth2UserRepository extends MongoRepository<Oauth2User, String>{
	
}
