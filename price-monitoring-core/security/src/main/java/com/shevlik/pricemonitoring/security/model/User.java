package com.shevlik.pricemonitoring.security.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table (name = "users")
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (name = "login")
	private String login;
	@Column (name = "password")
	private String password;
	@Column (name ="email")
	private String email;
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(name="users_roles", 
	         joinColumns = @JoinColumn(name = "users_id"), 
	         inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private Set<Role> roles;
}
