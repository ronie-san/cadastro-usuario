# API de Cadastro de Usuário

## Utilização de Flyway
O banco de dados foi implementado utilizando versionamento Flyway.
- A pasta _src/main/resources/dm/migration_ contém os scripts necessários para criação das tabelas necessárias
- O arquivo _src/main/resources/application.properties_ contém as configurações necessárias para conexão com o banco **MySQL**

#

## Documentação via Swagger
Utilizando o framework Swagger, é possível, ao executar a aplicação, visualizar os _Controllers_ utilizados na API, bem como seus métodos e suas respectivas URLs. Basta acessar

``/api/v1/swagger-ui.html``

#

## Descrição da API

### POST (``/api/v1/usuario``)
Este método realiza criação de usuários.
#### Body
~~~json
{
  "codigo": "string",
  "dtNascimento": "yyyy-MM-dd",
  "foto": "string",
  "nome": "string"
}
~~~

### GET (``/api/v1/usuario/{codigo}``)
Este método realiza a busca de algum usuário a partir do seu código.
#### Response Body
~~~json
{
  "codigo": "string",
  "dtNascimento": "yyyy-MM-dd",
  "foto": "string",
  "nome": "string"
}
~~~

### PUT (``/api/v1/usuario/{codigo}``)
Este método realiza a alteração de informações de um usuário a partir do seu código.
#### Body
~~~json
{
  "codigo": {{codigo}},
  "dtNascimento": "yyyy-MM-dd",
  "foto": "string",
  "nome": "string"
}
~~~
#### Response Body
~~~json
{
  "codigo": {{codigo}},
  "dtNascimento": "yyyy-MM-dd",
  "foto": "string",
  "nome": "string"
}
~~~

### DELETE (``/api/v1/usuario/{codigo}``)
Este método realiza a deleção de um usuário a partir do seu código.