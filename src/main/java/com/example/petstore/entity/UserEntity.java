package com.example.petstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "user")
public class UserEntity {

	public UserEntity() {
		super();
	}

	public UserEntity(Long id, @NotBlank String username, @NotBlank String firstName, @NotBlank String lastName,
			@NotBlank @Email String email, @NotBlank String password, @NotBlank String phone,
			@NotNull Integer userStatus) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userStatus = userStatus;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotBlank
	@Column(name = "username", unique = true)
	private String username;
	@NotBlank
	@Column(name = "firstname")
	private String firstName;
	@NotBlank
	@Column(name = "lastname")
	private String lastName;
	@Email(message = "Invalid email")
	@Column(name = "email", unique = true)
	@NotNull(message = "Email cant be empty")
	private String email;
	@NotBlank
	@Column(name = "password")
	private String password;
	@NotBlank
	@Column(name = "phone")
	private String phone;
	@NotNull
	@Column(name = "userstatus")
	private Integer userStatus;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

}
