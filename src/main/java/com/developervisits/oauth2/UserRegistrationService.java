package com.developervisits.oauth2;

import com.developervisits.oauth2.model.UserDto;
import com.developervisits.oauth2.entity.NormalUser;
import com.developervisits.oauth2.entity.Oauth2User;
import com.developervisits.oauth2.model.UserOAuth2Dto;
import com.developervisits.oauth2.repository.Oauth2UserRepository;
import com.developervisits.oauth2.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserRegistrationService {

	private  UserRepository repository;
	private  PasswordEncoder encoder;
	private  Oauth2UserRepository oauth2Repository;

	public void registerNewUser(@Lazy UserDto user) {
		NormalUser normalUser = new NormalUser(
				user.getUsername(), 
				user.getFirstname(), 
				user.getLastname(),
				user.getEmail(), 
				encoder.encode(user.getPassword()),
				encoder.encode(String.valueOf(user.getSecurityPin()))
		);
		normalUser.setVerified(true);
		repository.save(normalUser);
	}

	public void registerNewAuth2User(@Lazy UserOAuth2Dto userDto) {
		Oauth2User user = new Oauth2User(userDto.getUsername(),
													 userDto.getFirstname(),
													 userDto.getLastname(),
													 userDto.getEmail());
		oauth2Repository.save(user);
	}
	
}
