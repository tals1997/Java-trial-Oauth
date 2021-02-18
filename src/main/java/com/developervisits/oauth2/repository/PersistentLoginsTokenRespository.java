package com.developervisits.oauth2.repository;

import com.developervisits.oauth2.entity.PersistentLoginToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersistentLoginsTokenRespository extends MongoRepository<PersistentLoginToken, String> {

	PersistentLoginToken findBySeries(String series);
	PersistentLoginToken findByUsername(String username);
	
}
