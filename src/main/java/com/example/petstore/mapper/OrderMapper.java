package com.example.petstore.mapper;

import org.springframework.stereotype.Component;

import com.example.generated.model.Order;
import com.example.generated.model.Order.StatusEnum;
import com.example.petstore.entity.OrderEntity;

@Component
public class OrderMapper {
	
	public OrderEntity mapToEntity(Order order) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(order.getId());
		orderEntity.setPetId(order.getPetId());
		orderEntity.setQuantity(order.getQuantity());
		orderEntity.setShipDate(order.getShipDate());
		orderEntity.setStatus(order.getStatus().toString());
		orderEntity.setComplete(order.getComplete());
		
		return orderEntity;
	}

	public Order mapToDTO (OrderEntity orderEntity) {
		Order order = new Order();
		order.setId(orderEntity.getId());
		order.setPetId(orderEntity.getPetId());
		order.setQuantity(orderEntity.getQuantity());
		order.setShipDate(orderEntity.getShipDate());
		order.setStatus(StatusEnum.fromValue(orderEntity.getStatus()));
		order.setComplete(orderEntity.getComplete());
		
		return order;
	}
}
