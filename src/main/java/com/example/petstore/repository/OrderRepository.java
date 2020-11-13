package com.example.petstore.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petstore.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Serializable> {

}
