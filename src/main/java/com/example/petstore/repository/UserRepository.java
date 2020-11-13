package com.example.petstore.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petstore.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Serializable> {

	UserEntity findByUsername(String username);
}
