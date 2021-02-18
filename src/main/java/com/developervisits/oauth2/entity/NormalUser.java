package com.developervisits.oauth2.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Document
@RequiredArgsConstructor
@Getter
@ToString
public class NormalUser {

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
    @NonNull
    private String password;
    @Setter
    private boolean verified;
    @NonNull
    private String securityPin;

}
