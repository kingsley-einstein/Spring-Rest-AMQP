package com.spring.rest.amqp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Table(name = "users")
@Entity
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid email format")
    @NotNull
    @NotEmpty
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}