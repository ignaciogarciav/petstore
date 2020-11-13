package com.example.petstore.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.generated.model.Order;
import com.example.generated.model.ResponseApi;

@Service
public interface OrderService {

	ResponseApi placerOrder(Order order);
	
	Map<String, Integer> getInventory();
	
	Order getOrderById(Long id);
	
	ResponseApi deleteOrderById(Long id);
}
