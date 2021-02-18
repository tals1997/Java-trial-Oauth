package com.developervisits.oauth2.config;

import com.developervisits.oauth2.userDetails.AdditionalAuthenticationDetailsSource;
import com.developervisits.oauth2.AdditionalAuthenticationProvider;
import com.developervisits.oauth2.userDetails.FacebookConnectUser;
import com.developervisits.oauth2.userDetails.Oauth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdditionalAuthenticationProvider additionalProvider;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;
    @Autowired
    private Oauth2AuthenticationSuccessHandler oauthSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/login",true)
                .authenticationDetailsSource(new AdditionalAuthenticationDetailsSource())
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository)
                .and()
                .oauth2Login()
                .loginPage("/login")
                .successHandler(oauthSuccessHandler)
                .userInfoEndpoint()
                .customUserType(FacebookConnectUser.class, "facebook")
                .and()
                .and()
                .authorizeRequests()
                .mvcMatchers("/register","/login","/login-error",
                        "/login-verified").permitAll();

        // @formatter:on
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(additionalProvider);
    }

    @Bean
    public RedirectStrategy getRedirectStrategy() {
        return new DefaultRedirectStrategy();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
