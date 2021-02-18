package com.developervisits.oauth2.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Document
@RequiredArgsConstructor
@Getter
@ToString
public class Oauth2User {

    @Id
    private String id;
    @NonNull
    @Indexed(unique=true)
    private final String username;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Email
    @NonNull
    private String email;

}