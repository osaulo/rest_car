# Desafio 
API RESTful para Sistema de Usuários de carro

# Motivação
Criar aplicação que exponha uma API RESTful de criação de usuários e carros com login.

# Descrição do sistema
Criar uma API para gerenciamento de carros dos usuarios.

- Modelo de dados
    - Usuario
        * firstName   String  Nome do usuário 
        * lastName    String  Sobrenome do usuário 
        * email       String  E-mail do usuário 
        * birthday    Date    Data de nascimento do usuário 
        * login       String  Login do usuário 
        * password    String  Senha do usuário 
        * phone       String  Telefone do usuário
        * cars        List    Lista de carros do usuário

    - Carro
        * year            Int     Ano de fabricação do carro 
        * licensePlate    String  Placa do carro 
        * model           String  Modelo do carro 
        * color           String  Cor predominante do carro

# Cronograma de atividades
- 21/11
    * desenvolvimento das estorias - ok
    * desenvolvimento testes junit / cumcumber
    * desenvolvimento do eureka - 
    * desenvolvimento do swagger - 

- 22/11
    * desenvolvimento da autenticacao
    * desenvolvimento do crud
    * desenvolvimento do scheduled

- 23/11
    * desenvolvimento front-end
    * integracao sonar
    * integracao jenkins
    * integracao heroku

# Estorias de usuario

- **Funcionalidade**: Realizar login       
/api/signin     POST
   - **Como** um usuario cadastrado do sistema
   - **Eu Quero** logar no sistema 
   - **Para** ter acesso a outras funcionalidades do sistema
   - **Cenario 1:** Logar no sistema
       - **Dado** que sou usuario do sistema
       - **E** cadastrado no sistema
       - **Quando** logar no sistema
       - **Entao** devo receber o token de acesso
   - **Cenario 2:** Logar no sistema com dados incorretos
       - **Dado** que sou usuario do sistema
       - **E** cadastrado no sistema
       - **E** defino usuario/senha errado
       - **Quando** logar no sistema
       - **Entao** devo receber "Invalid login or password"
        
- **Funcionalidade**: Listar todos os usuários
/api/users      GET
   - **Como** um usuario do sistema
   - **Eu Quero** listar todos os usuarios registrados
   - **Cenario 1:** Listar todos os usuarios registrados
       - **Dado** sou um usuario do sistema
       - **Quando** listar todos os usuarios registrados
       - **Entao** devo receber a lista de usuarios
        
- **Funcionalidade**: Cadastrar um novo usuário
/api/users      POST
   - **Como** um usuario do sistema
   - **Eu Quero** me cadastrar no sistema
   - **Para** ter acesso
   - **Cenario 1:** Cadastrar um novo usuário 
       - **Dado** sou um usuario do sistema
       - **E** defino os dados do novo usuario
       - **Quando** realizar cadastro
       - **Entao** devo receber id e token do novo usuario
   - **Cenario 2:** Cadastrar um novo usuário com email ja existente
       - **Dado** sou um usuario do sistema
       - **E** defino email ja existente
       - **Quando** realizar cadastro
       - **Entao** devo receber "Email already exists"
   - **Cenario 3:** Cadastrar um novo usuário com login ja existente
       - **Dado** sou um usuario do sistema
       - **E** defino login ja existente
       - **Quando** realizar cadastro
       - **Entao** devo receber "Login already exists"
   - **Cenario 4:** Cadastrar um novo usuário com campos invalidos
       - **Dado** sou um usuario do sistema
       - **E** defino email invalido
       - **Quando** realizar cadastro
       - **Entao** devo receber "Invalid fields"
   - **Cenario 5:** Cadastrar um novo usuário com campos invalidos
       - **Dado** sou um usuario do sistema
       - **E** defino birthday invalido
       - **Quando** realizar cadastro
       - **Entao** devo receber "Invalid fields"
   - **Cenario 6:** Cadastrar um novo usuário com campos nao preenchidos
       - **Dado** sou um usuario do sistema
       - **E** defino todos os campos do usuario vazio
       - **Quando** realizar cadastro
       - **Entao** devo receber "Missing fields"

- **Funcionalidade**: Buscar um usuário pelo id
/api/users/{id} GET
   - **Como** um usuario do sistema
   - **Eu Quero** buscar um usuario
   - **Cenario 1:** Buscar um usuario pelo id
       - **Dado** sou um usuario do sistema
       - **E** defino "id"
       - **Quando** buscar um usuario
       - **Entao** devo receber os dados do usuario

- **Funcionalidade**: Remover um usuário pelo id
/api/users/{id} DELETE
   - **Como** um usuario do sistema
   - **Eu Quero** remover um usuario
   - **Para** nao deixa-lo ter acesso ao sistema
   - **Cenario 1:** Remover um usuario pelo id
       - **Dado** sou um usuario do sistema
       - **E** defino id
       - **Quando** remover um usuario
       - **Entao** devo receber o status ok
   - **Cenario 2:** Remover um usuario pelo id inexistente
       - **Dado** sou um usuario do sistema
       - **E** defino id inexistente
       - **Quando** remover um usuario
       - **Entao** devo receber "user nonexistent"

- **Funcionalidade**: Atualizar um usuário pelo id
/api/users/{id} PUT
   - **Como** um usuario do sistema
   - **Eu Quero** atualizar um usuario
   - **Cenario 1:** Atualizar um usuario pelo id
       - **Dado** sou um usuario do sistema
       - **E** defino id
       - **E** defino firstName
       - **E** defino lastName
       - **E** defino email
       - **E** defino birthday
       - **E** defino login
       - **E** defino password
       - **E** defino phone
       - **E** defino cars
       - **Quando** atualizar um usuario
       - **Entao** devo receber os novos dados do usuario
   - **Cenario 2:** Atualizar um usuario pelo id com email ja existente
       - **Dado** sou um usuario do sistema
       - **E** defino id
       - **E** defino email ja existente
       - **Quando** atualizar um usuario
       - **Entao** devo receber "Email already exists"
   - **Cenario 3:** Atualizar um usuario pelo id com login ja existente
       - **Dado** sou um usuario do sistema
       - **E** defino id
       - **E** defino login ja existente
       - **Quando** atualizar um usuario
       - **Entao** devo receber "login already exists"
   - **Cenario 4:** Atualizar um usuario pelo id com campos invalidos
       - **Dado** sou um usuario do sistema
       - **E** defino id
       - **E** defino email invalido
       - **Quando** atualizar um usuario
       - **Entao** devo receber "Invalid fields"
   - **Cenario 5:** Atualizar um usuario pelo id com campos invalidos
       - **Dado** sou um usuario do sistema
       - **E** defino id
       - **E** defino birthday invalido
       - **Quando** atualizar um usuario
       - **Entao** devo receber "Invalid fields"
   - **Cenario 6:** Atualizar um usuario pelo id com campos nao preenchidos
       - **Dado** sou um usuario do sistema
       - **E** defino id
       - **E** defino todos os campos do usuario vazio
       - **Quando** atualizar um usuario
       - **Entao** devo receber "Missing fields"
   - **Cenario 6:** Atualizar um usuario pelo id inexistente
       - **Dado** sou um usuario do sistema
       - **E** defino id inexistente
       - **E** defino firstName
       - **E** defino lastName
       - **E** defino email
       - **E** defino birthday
       - **E** defino login
       - **E** defino password
       - **E** defino phone
       - **E** defino cars
       - **Quando** atualizar um usuario
       - **Entao** devo receber "user nonexistent"

- **Funcionalidade**: Retornar as informações do usuário logado
/api/me     GET
   - **Como** um usuario cadastrado do sistema
   - **Eu Quero** recuperar as informações do usuário
   - **Para** acessar os dados do usuario
   - **Cenario 1:** Recuperar as informações do usuário logado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino token
       - **Quando** recuperar as informações do usuário
       - **Entao** devo receber os dados do usuario logado
   - **Cenario 2:** Recuperar as informações do usuário logado com token nao enviado
       - **Dado** sou usuario do sistema
       - **E** defino token vazio
       - **Quando** recuperar as informações do usuário
       - **Entao** devo receber "Unauthorized"
   - **Cenario 3:** Recuperar as informações do usuário logado com token expirado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema por mais de 5 minutos
       - **Quando** recuperar as informações do usuário
       - **Entao** devo receber "Unauthorized - invalid session"

- **Funcionalidade**: Listar todos os carros do usuário logado
/api/cars   GET
   - **Como** um usuario cadastrado do sistema
   - **Eu Quero** listar todos os carros do usuário logado
   - **Para** acessar os dados da lista de carros
   - **Cenario 1:** Listar todos os carros do usuário logado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **Quando** listar todos os carros do usuário logado
       - **Entao** devo receber a lista de carros do usuário logado
   - **Cenario 2:** Listar todos os carros do usuário logado com token nao enviado
       - **Dado** sou usuario do sistema
       - **E** defino token vazio
       - **Quando** listar todos os carros do usuário logado
       - **Entao** devo receber "Unauthorized"
   - **Cenario 3:** Listar todos os carros do usuário logado com token expirado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema por mais de 5 minutos
       - **Quando** listar todos os carros do usuário logado
       - **Entao** devo receber "Unauthorized - invalid session"

- **Funcionalidade**: Cadastrar um novo carro para o usuário logado
/api/cars   POST
   - **Como** um usuario cadastrado do sistema
   - **Eu Quero** cadastrar um novo carro
   - **Cenario 1:** Cadastrar um novo carro para o usuário logado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino os dados do novo carro
       - **Quando** cadastrar um novo carro
       - **Entao** devo receber id do novo carro cadastrado
   - **Cenario 2:** Cadastrar um novo carro para o usuário logado com token nao enviado
       - **Dado** sou usuario do sistema
       - **E** defino token vazio
       - **E** defino os dados do novo carro
       - **Quando** cadastrar um novo carro
       - **Entao** devo receber "Unauthorized"
   - **Cenario 3:** Cadastrar um novo carro para o usuário logado com token expirado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema por mais de 5 minutos
       - **E** defino os dados do novo carro
       - **Quando** cadastrar um novo carro
       - **Entao** devo receber "Unauthorized - invalid session"
   - **Cenario 4:** Cadastrar um novo carro para o usuário logado com placa ja existente
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino placa ja existente
       - **Quando** cadastrar um novo carro
       - **Entao** devo receber "“License plate already exists"
   - **Cenario 5:** Cadastrar um novo carro para o usuário logado com dados invalidos
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino placa invalida
       - **Quando** cadastrar um novo carro
       - **Entao** devo receber "Invalid fields"
   - **Cenario 6:** Cadastrar um novo carro para o usuário logado com campos nao preenhidos
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino todos os campos do carro vazio
       - **Quando** cadastrar um novo carro
       - **Entao** devo receber "Missing fields"

- **Funcionalidade**: Buscar um carro do usuário logado pelo id
/api/cars/{id}  GET
   - **Como** um usuario cadastrado do sistema
   - **Eu Quero** buscar um carro pelo id
   - **Para** acessar os dados do carro
   - **Cenario 1:** Buscar um carro do usuário logado pelo id
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino id
       - **Quando** buscar um carro
       - **Entao** devo receber os dados do carro
   - **Cenario 2:** Buscar um carro do usuário logado pelo id com token nao enviado
       - **Dado** sou usuario do sistema
       - **E** defino token vazio
       - **E** defino id
       - **Quando** buscar um carro
       - **Entao** devo receber "Unauthorized"
   - **Cenario 3:** Buscar um carro do usuário logado pelo id com token expirado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema por mais de 5 minutos
       - **E** defino id
       - **Quando** buscar um carro
       - **Entao** devo receber "Unauthorized - invalid session"

- **Funcionalidade**: Remover um carro do usuário logado pelo id
/api/cars/{id}  DELETE
   - **Como** um usuario cadastrado do sistema
   - **Eu Quero** remover um carro do usuário logado pelo id
   - **Cenario 1:** Remover um carro do usuário logado pelo id
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **Quando** remover um carro pelo id
       - **Entao** devo receber o status ok

- **Funcionalidade**: Atualizar um carro do usuário logado pelo id
/api/cars/{id}  PUT
   - **Como** um usuario cadastrado do sistema
   - **Eu Quero** atualizar os dados carro
   - **Para** manter os dados atualizados
   - **Cenario 1:** Atualizar um carro para o usuário logado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino os dados atualizados do carro
       - **Quando** atualizar um carro
       - **Entao** devo receber os dados do carro atualizado
   - **Cenario 2:** Atualizar um carro para o usuário logado com token nao enviado
       - **Dado** sou usuario do sistema
       - **E** defino token vazio
       - **E** defino os dados atualizados do carro
       - **Quando** atualizar um carro
       - **Entao** devo receber "Unauthorized"
   - **Cenario 3:** Atualizar um carro para o usuário logado com token expirado
       - **Dado** sou usuario do sistema
       - **E** logado no sistema por mais de 5 minutos
       - **E** defino os dados atualizados do carro
       - **Quando** atualizar um carro
       - **Entao** devo receber "Unauthorized - invalid session"
   - **Cenario 4:** Atualizar um carro para o usuário logado com placa ja existente
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino placa ja existente
       - **Quando** atualizar um carro
       - **Entao** devo receber "“License plate already exists"
   - **Cenario 5:** Atualizar um carro para o usuário logado com dados invalidos
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino placa invalida
       - **Quando** atualizar um carro
       - **Entao** devo receber "Invalid fields"
   - **Cenario 6:** Atualizar um carro para o usuário logado com campos nao preenhidos
       - **Dado** sou usuario do sistema
       - **E** logado no sistema
       - **E** defino todos os campos do carro vazio
       - **Quando** atualizar um carro
       - **Entao** devo receber "Missing fields"

# Tecnologias requeridas
- JWT como token;
- Servidor deve estar embutido na aplicação (Tomcat, Undertow ou Jetty);
- Processo de build via Maven;
- Banco de dados em memória, como H2;
- Framework Spring;
- Utilizar no mínimo Java 8;
- Persistência com JPA/Hibernate;
- Disponibilizar a API rodando em algum host (Heroku, AWS, Digital Ocean, etc);
- Testes unitários;
- Criar um repositório público em alguma ferramenta de git (Github, Gitlab, Bitbucket, etc);
- Senha deve ser criptografada;
- Documentação (Javadoc);
- Design Pattern;
- Pipeline no Jenkins;
- Integração com SonarQube;
- Integração com JFrog Artifactory ou Nexus;
- Balanceador de cargas, Service Registry e/ou Proxy (Eureka, Zuul, Camel, Helix, etc.);
- Swagger.


# Detalhe da solução
- API Rest desenvolvida em Spring
- Necessário autenticação para consumir os serviços da API. Utilizado JWT. No endpoint de autenticação é necessário informar e-mail e password. Já estão criados por padrão dois usuários, um de perfil ADMIN que pode listar, criar, atualizar e remover outros usuários. E um de perfil TECHNICIAN, que é o perfil responsável pelas interações com o estacionamento, ou seja, ele pode:
xxxxxxxxxx
- Como banco de dados está sendo utilizado o H2, banco que sobe em tempo de execução da aplicação: http://
    * Usuário: sa
    * Password:
    * BD: restcarDB
- O registro do microservico esta no eureka, disponivel em: http://
- Documentação da API utilizando Swagger, disponível em: http://
- Deploys da aplicação disponível no Heroku. Swagger: https://