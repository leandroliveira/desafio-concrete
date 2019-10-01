package com.app.desafioconcrete.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.desafioconcrete.api.entities.Phone;
import com.app.desafioconcrete.api.entities.User;
import com.app.desafioconcrete.api.repository.PhoneRepository;
import com.app.desafioconcrete.api.repository.UserRepository;
import com.app.desafioconcrete.api.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

   private final String USER_EMAIL1 = "leandro@teste.com";
   private final String PASSWORD1 = "teste123";
   private final String USER_EMAIL2 = "joao@teste.com";
   private final String PASSWORD2 = "teste12345";
   
   @Autowired
    UserService service;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PhoneRepository phoneRepository;
    
    @Before
    public void beforeEach() throws NoSuchAlgorithmException, UnsupportedEncodingException {

    	this.cadastrarUser(USER_EMAIL1, PASSWORD1);
		
    }
    
    public void cadastrarUser(String email, String password) 
    		throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	User user = new User();
    	user = userRepository.findByEmail(email);
    	//So cadastra se usuario não existir
    	if(user == null) {
    		user.setEmail(email);
    		user.setPassword(service.getEncrypted("teste123"));
    		user.setCriated(LocalDate.now());
    		user.setModified(LocalDateTime.now());
    		user.setLast_login(LocalDateTime.now());
    		user.generateToken();
    		
    		user = userRepository.save(user);
    		
    		Phone phone = new Phone();
    		phone.setDdd("81");
    		phone.setNumber("996559865");
    		//linkamos os telefones com os usuarios 
    		List<Phone> arrPhones = new ArrayList<Phone>();
    		arrPhones.add(phone);
    		
    		user.setPhones(arrPhones);
    		
    		phoneRepository.saveAll(user.getPhones());
    	}
    }

   
    @Test
	public void usuarioInformadoDeveEstarCadastrado() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		assertNotNull("E-mail já existente", userRepository.findByEmail(USER_EMAIL1));
	}
    
    @Test
	public void usuarioInformadoNaoConstaNaBase() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		assertNull("E-mail já existente", userRepository.findByEmail(USER_EMAIL2));
	}
    
    @Test
	public void passwordInformadoInvalido() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		boolean isPasswordValido = false;
    	String passwordFake = service.getEncrypted(PASSWORD2);
    	
    	User user = new User();
    	user = userRepository.findByEmail(USER_EMAIL1);
    	isPasswordValido = user.getPassword().toString().equals(passwordFake);
    	
		assertFalse("Os passwords são iguais", isPasswordValido);
	}
}
