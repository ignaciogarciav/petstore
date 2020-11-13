package com.example.petstore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.generated.model.Order;
import com.example.generated.model.ResponseApi;
import com.example.petstore.entity.OrderEntity;
import com.example.petstore.exceptions.ResourceNotFoundException;
import com.example.petstore.mapper.OrderMapper;
import com.example.petstore.repository.OrderRepository;
import com.example.petstore.service.OrderService;
import com.example.petstore.utils.ResponseApiFactory;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ResponseApiFactory responseFactory;

	@Override
	public ResponseApi placerOrder(Order order) {
		orderRepository.save(orderMapper.mapToEntity(order));
		return responseFactory.createNew201Response();
	}

	@Override
	public Order getOrderById(Long id) {
		Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
		if (orderEntityOptional.isPresent()) {
			return orderMapper.mapToDTO(orderEntityOptional.get());
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public ResponseApi deleteOrderById(Long id) {
		Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
		if (orderEntityOptional.isPresent()) {
			orderRepository.delete(orderEntityOptional.get());
			return responseFactory.createNew200Response();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public Map<String, Integer> getInventory() {
		Map<String, Integer> inventory = new HashMap<>();
		List<OrderEntity> orderEntityList = orderRepository.findAll();
		for (OrderEntity orderEntity : orderEntityList) {
			String status = orderEntity.getStatus();
			if (inventory.containsKey(status)) {
				inventory.put(status, inventory.get(status) + orderEntity.getQuantity());
			} else {
				inventory.put(status, orderEntity.getQuantity());
			}
		}
		if (inventory.isEmpty()) {
			throw new ResourceNotFoundException();
		} else {
			return inventory;
		}
	}

}
