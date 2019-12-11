package com.bitc502.grapemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

}
