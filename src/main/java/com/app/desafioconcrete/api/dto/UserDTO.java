package com.app.desafioconcrete.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.app.desafioconcrete.api.entities.Phone;

/* Campos retornados de acordo com a solicitação
 
"name": "João da Silva",
"email": "joao@silva.org",
"password": "hunter2",
"phones": [
    {
        "number": "987654321",
        "ddd": "21"
    }
]
}
id: id do usuário (pode ser o próprio gerado pelo banco, porém seria interessante se fosse um UUID)
created: data da criação do usuário
modified: data da última atualização do usuário
last_login: data do último login (no caso da criação, será a mesma que a criação)
token: token de acesso da API (pode ser um UUID ou um JWT)
*/

public class UserDTO {

	private String name;
	private String email;
	private List<Phone> phones;
	private UUID id;
	private LocalDate created;
    private LocalDateTime last_login;
    private LocalDateTime modified;
    private UUID token;
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
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> arrPhones) {
		this.phones = arrPhones;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public LocalDate getCreated() {
		return created;
	}
	
	public void setCreated(LocalDate dtUserCriated) {
		this.created = dtUserCriated;
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
	
	public UUID getToken() {
		return token;
	}
	public void setToken(UUID token) {
		this.token = token;
	} 
    
    
}
