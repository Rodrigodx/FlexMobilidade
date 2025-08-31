# FlexMobilidade

Este projeto é uma aplicação backend desenvolvida em Java com Spring Boot, projetada para gerenciar dados relacionados a uma empresa de mobilidade. Ele oferece funcionalidades para o gerenciamento de carros, acessórios, proteções, utilitários adicionais, categorias e usuários, além de um sistema de reservas. A aplicação segue uma arquitetura RESTful, provendo uma API robusta para integração com sistemas frontend ou outros serviços.

## Tecnologias Utilizadas

*   **Java 17**: Linguagem de programação principal.
*   **Spring Boot 3.0.5**: Framework para construção de aplicações Java robustas e escaláveis.
*   **Spring Data JPA**: Para persistência de dados e interação com o banco de dados.
*   **Spring Security**: Para autenticação e autorização baseada em JWT (JSON Web Tokens).
*   **MySQL Connector/J**: Driver JDBC para conexão com o banco de dados MySQL.
*   **H2 Database**: Banco de dados em memória utilizado para testes.
*   **Lombok**: Biblioteca para reduzir código boilerplate.
*   **ModelMapper**: Para mapeamento de objetos (DTOs para entidades e vice-versa).
*   **Hibernate Validator**: Para validação de dados.
*   **JJWT (Java JWT)**: Para manipulação de tokens JWT.
*   **Springdoc OpenAPI**: Para geração automática de documentação da API (Swagger UI).

## Estrutura do Projeto

O projeto segue a estrutura padrão de aplicações Spring Boot, organizada em pacotes que representam as diferentes camadas da aplicação:

*   `com.rodrigo.flexmobilidade.controllers`: Contém os controladores REST que expõem os endpoints da API.
*   `com.rodrigo.flexmobilidade.services`: Contém a lógica de negócio da aplicação.
*   `com.rodrigo.flexmobilidade.repositories`: Contém as interfaces de repositório para acesso a dados.
*   `com.rodrigo.flexmobilidade.model`: Contém as entidades de domínio que representam as tabelas do banco de dados.
*   `com.rodrigo.flexmobilidade.dto`: Contém os objetos de transferência de dados (DTOs) utilizados nas requisições e respostas da API.
*   `com.rodrigo.flexmobilidade.infra.security`: Contém a configuração de segurança e classes relacionadas a JWT.
*   `com.rodrigo.flexmobilidade.exceptions`: Contém classes de exceção personalizadas.
*   `com.rodrigo.flexmobilidade.utility`: Contém classes utilitárias, como `Constants.java`.

## Como Rodar o Projeto

Para rodar este projeto localmente, siga os passos abaixo:

### Pré-requisitos

*   Java Development Kit (JDK) 17 ou superior.
*   Maven 3.x.
*   Um servidor de banco de dados MySQL (opcional, para ambiente de produção/desenvolvimento).

### Configuração do Banco de Dados

O projeto está configurado para usar MySQL. Você precisará configurar as credenciais do banco de dados no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flexmobilidade_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Substitua `seu_usuario` e `sua_senha` pelas suas credenciais do MySQL. Se você não tiver um banco de dados MySQL configurado, o projeto pode ser executado com o H2 em memória para testes, mas para persistência de dados, o MySQL é recomendado.

### Construção e Execução

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/Rodrigodx/FlexMobilidade
    cd FlexMobilidade
    ```

2.  **Construa o projeto usando Maven:**

    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação Spring Boot:**

    ```bash
    mvn spring-boot:run
    ```

    A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

A documentação interativa da API está disponível via Swagger UI após a execução da aplicação:

`http://localhost:8080/swagger-ui.html`

Os principais endpoints incluem:

*   `/api/auth/register`: Registro de novos usuários.
*   `/api/auth/authenticate`: Autenticação de usuários e obtenção de JWT.
*   `/api/cars`: Gerenciamento de carros.
*   `/api/accessories`: Gerenciamento de acessórios.
*   `/api/protections`: Gerenciamento de proteções.
*   `/api/additional-utilities`: Gerenciamento de utilitários adicionais.
*   `/api/categories`: Gerenciamento de categorias.
*   `/api/reservations`: Gerenciamento de reservas.
*   `/api/users`: Gerenciamento de usuários (requer permissões de administrador).

## Testes

Os testes unitários e de integração podem ser executados usando Maven:

```bash
mvn test
```

## Contribuição

Contribuições são bem-vindas! Por favor, siga estas etapas:

1.  Faça um fork do repositório.
2.  Crie uma nova branch (`git checkout -b feature/sua-feature`).
3.  Faça suas alterações e commit (`git commit -am 'Adiciona nova feature'`).
4.  Envie para a branch original (`git push origin feature/sua-feature`).
5.  Crie um Pull Request.
