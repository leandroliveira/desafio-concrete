<b>Desafio Concrete Solutions</b>

Criação de uma aplicação que exponha uma API RESTful de criação de usuários, login e acesso ao perfil. A constrção deste desafio faz parte de uma seleção para a empresa Concrete Solutions.
Endpoints
Todos os endpoints devem aceitar e responder somente JSON, inclusive ao responder mensagens de erro.

 {"mensagem": "mensagem de erro"}
 
<b>Cadastro</b>

   Endpoint: https://desafioconcrete-leandro.herokuapp.com/api/user/cadastro

   Formato da requisição:
    {

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

<b>Login</b>

    Endpoint: https://desafioconcrete-leandro.herokuapp.com/api/user/login

    Formato da requisição:
    {

       "email": "joao@silva.org",
       "password": "hunter2"
    }

<b>Perfil</b>

   Endpoint: https://desafioconcrete-leandro.herokuapp.com/api/user/perfil

    Formato da requisição:
    {

        "idUser": "055d0299-77bd-4636-becb-9610c6b2bb77", 
        "token": "0e04c0be-bbfe-4965-ac86-763ad0a62c29"
    }

Tecnologias Utilizadas.</br>
H2 (Banco de dados em memória).</br>
Processo de build via Gradle.</br>
Java 8.</br>
Framework Spring Boot.</br>
Persistência com Spring Data e Hibernate.</br>
Cloud host Heroku.</br>
Postman.</br>
Servidor Tomcat Embedded.</br>
SHA-256 (Método de criptografia hash).</br>

<b>Autor</b></br>
 Leandro Araújo
