package com.developervisits.oauth2;

import org.springframework.stereotype.Component;

@Component
public interface ExistingUserService {

    boolean userExists (String username);
}
