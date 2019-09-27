package com.app.desafioconcrete.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.app.desafioconcrete.api.entities.Phone;

/* Campos retornados de acordo com a solicita��o
 
"name": "Jo�o da Silva",
"email": "joao@silva.org",
"password": "hunter2",
"phones": [
    {
        "number": "987654321",
        "ddd": "21"
    }
]
}
Responder o c�digo de status HTTP apropriado

Em caso de sucesso, retorne o usu�rio, mais os campos:

id: id do usu�rio (pode ser o pr�prio gerado pelo banco, por�m seria interessante se fosse um UUID)
created: data da cria��o do usu�rio
modified: data da �ltima atualiza��o do usu�rio
last_login: data do �ltimo login (no caso da cria��o, ser� a mesma que a cria��o)
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
