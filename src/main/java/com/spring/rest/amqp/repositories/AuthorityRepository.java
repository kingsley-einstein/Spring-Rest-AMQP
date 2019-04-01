package com.spring.rest.amqp.repositories;

import com.spring.rest.amqp.models.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}