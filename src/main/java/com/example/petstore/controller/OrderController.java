package com.example.petstore.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.generated.api.StoreApi;
import com.example.generated.model.Order;
import com.example.generated.model.ResponseApi;
import com.example.petstore.service.OrderService;

@RestController
public class OrderController implements StoreApi{
	
	@Autowired
	private OrderService orderService;

	@Override
	public ResponseEntity<ResponseApi> deleteOrder(@Min(1) Long orderId) {
		return ResponseEntity.ok(orderService.deleteOrderById(orderId));
	}

	@Override
	public ResponseEntity<Map<String, Integer>> getInventory() {
		return ResponseEntity.ok(orderService.getInventory());
	}

	@Override
	public ResponseEntity<Order> getOrderById(@Min(1) @Max(10) Long orderId) {
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}

	@Override
	public ResponseEntity<ResponseApi> placeOrder(@Valid Order body) {
		return new ResponseEntity<ResponseApi>(orderService.placerOrder(body), HttpStatus.CREATED);
	}

}
