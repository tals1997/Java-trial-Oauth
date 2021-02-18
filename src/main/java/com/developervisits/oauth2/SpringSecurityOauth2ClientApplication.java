package com.developervisits.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, WebMvcAutoConfiguration.class })
@ComponentScan({"com.developervisits.oauth2.controller", "com.developervisits.oauth2.service"})
public class SpringSecurityOauth2ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOauth2ClientApplication.class, args);
	}

}
