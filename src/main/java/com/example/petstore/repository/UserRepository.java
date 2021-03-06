package com.example.petstore.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petstore.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Serializable> {

	Optional<UserEntity> findByUsername(String username);
}
