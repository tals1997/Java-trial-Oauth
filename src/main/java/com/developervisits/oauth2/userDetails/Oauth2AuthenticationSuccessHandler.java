package com.developervisits.oauth2.userDetails;

import com.developervisits.oauth2.model.UserOAuth2Dto;
import com.developervisits.oauth2.ExistingUserService;
import com.developervisits.oauth2.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration("oauth2authSuccessHandler")
@RequiredArgsConstructor
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private final RedirectStrategy redirectStrategy;
	private final UserRegistrationService userRegistrationService;
	private final ExistingUserService existingUserService;

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		if(!this.existingUserService.userExists(authentication.getName())) {
			UserAuthenticatedPrincipal principal = (UserAuthenticatedPrincipal)authentication.getPrincipal();
			UserOAuth2Dto user = new UserOAuth2Dto(principal.getFirstName(),principal.getLastName(),authentication.getName(),principal.getEmail());
			this.userRegistrationService.registerNewAuth2User(user);
		}
		this.redirectStrategy.sendRedirect(request, response, "/home");
	}
}
