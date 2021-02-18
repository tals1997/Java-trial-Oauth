package com.developervisits.oauth2.controller;

import com.developervisits.oauth2.UserRegistrationService;
import com.developervisits.oauth2.model.UserDto;
import com.developervisits.oauth2.repository.Oauth2UserRepository;
import com.developervisits.oauth2.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserRegistrationService registrationService;

    @Bean
    public UserRegistrationService userRegistrationService(){
        return new UserRegistrationService();
    }

    @GetMapping("/register")
    public String register(@Lazy Model model) {
        model.addAttribute("user",new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute ("user")@NonNull @Lazy UserDto user, @Lazy BindingResult result) {
        if(result.hasErrors()) {
            return "register";
        }
        this.registrationService.registerNewUser(user);

        return "redirect: register ? success";
    }


}
