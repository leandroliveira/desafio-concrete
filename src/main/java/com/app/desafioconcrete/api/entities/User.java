package com.app.desafioconcrete.api.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	public User() {
        super();
	}
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
	
	private UUID token;
	
	private String name;
	
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Phone> phones;
	
	private LocalDate created;
	
	private LocalDateTime modified;
    
	private LocalDateTime last_login;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> arrPhones) {
		this.phones = arrPhones;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCriated(LocalDate pUserCreated) {
		this.created = pUserCreated;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime pModified) {
		this.modified = pModified;
	}
	
	public LocalDateTime getLast_login() {
		return last_login;
	}

	public void setLast_login(LocalDateTime lastUserLogin) {
		this.last_login = lastUserLogin;
	}

	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID userToken) {
		this.token = userToken;
	}
    
	public void generateToken() {
        this.token = UUID.randomUUID();
    }
}
