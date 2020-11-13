package com.example.petstore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class OrderEntity {

	public OrderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderEntity(Long id, @NotNull Long petId, @NotNull Integer quantity, @NotNull Date shipDate,
			@NotBlank String status, boolean isComplete) {
		super();
		this.id = id;
		this.petId = petId;
		this.quantity = quantity;
		this.shipDate = shipDate;
		this.status = status;
		this.isComplete = isComplete;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
	@NotNull
	@Column(name = "pet_id")
	private Long petId;
	@NotNull
	@Column(name = "quantity")
	private Integer quantity;
	@NotNull
	@Column(name = "shipdate")
	private Date shipDate;
	@NotBlank
	@Column(name = "status")
	private String status;
	@Column(name = "completed")
	private boolean isComplete = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
