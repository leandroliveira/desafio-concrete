package com.app.desafioconcrete.api.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;
	
	@Column(columnDefinition = "VARCHAR(255)")
    private UUID userToken;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private List<Phone> arrPhones;
	
	private LocalDate dtUserCriated;
	
    private LocalDateTime lastUserLogin;
    
    private LocalDateTime dtCreationModified;

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

	public List<Phone> getArrPhones() {
		return arrPhones;
	}

	public void setArrPhones(List<Phone> arrPhones) {
		this.arrPhones = arrPhones;
	}

	public LocalDate getDtUserCriated() {
		return dtUserCriated;
	}

	public void setDtUserCriated(LocalDate dtUserCriated) {
		this.dtUserCriated = dtUserCriated;
	}

	public LocalDateTime getLastUserLogin() {
		return lastUserLogin;
	}

	public void setLastUserLogin(LocalDateTime lastUserLogin) {
		this.lastUserLogin = lastUserLogin;
	}

	public LocalDateTime getDtCreationModified() {
		return dtCreationModified;
	}

	public void setDtCreationModified(LocalDateTime dtCreationModified) {
		this.dtCreationModified = dtCreationModified;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getUserToken() {
		return userToken;
	}

	public void setUserToken(UUID userToken) {
		this.userToken = userToken;
	}
    
	public void generateToken() {
        this.userToken = UUID.randomUUID();
    }
}
