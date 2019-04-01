package com.spring.rest.amqp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Table(name = "authorities")
@Entity
public class Authority implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid email format")
    @NotEmpty
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")
    private String role;

    @JoinColumn(name = "user_email", unique = true)
    @OneToOne
    private User user;

    public Authority(String email, String role, User user) {
        this.email = email;
        this.role = role;
        this.user = user;
    }
}