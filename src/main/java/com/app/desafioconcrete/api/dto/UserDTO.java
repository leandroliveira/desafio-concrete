package com.app.desafioconcrete.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
Responder o código de status HTTP apropriado

Em caso de sucesso, retorne o usuário, mais os campos:

id: id do usuário (pode ser o próprio gerado pelo banco, porém seria interessante se fosse um UUID)
created: data da criação do usuário
modified: data da última atualização do usuário
last_login: data do último login (no caso da criação, será a mesma que a criação)
token: token de acesso da API (pode ser um UUID ou um JWT)
*/

public class UserDTO {

	private String name;
	private String email;
	private List<Phone> arrPhones;
	private UUID id;
	private LocalDate dtUserCriated;
    private LocalDateTime lastUserLogin;
    private LocalDateTime dtCreationModified;
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
	public List<Phone> getArrPhones() {
		return arrPhones;
	}
	public void setArrPhones(List<Phone> arrPhones) {
		this.arrPhones = arrPhones;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public UUID getToken() {
		return token;
	}
	public void setToken(UUID token) {
		this.token = token;
	} 
    
    
}
