# 🏦 Bytebank API - Spring Boot

> Uma API RESTful robusta para um sistema bancário digital, construída com Spring Boot, aplicando boas práticas de arquitetura em camadas, encapsulamento, persistência de dados e segurança corporativa.

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0+-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

## Sobre o Projeto

Este projeto representa a evolução de um sistema bancário em Java puro para uma **API REST moderna e escalável**. Ele faz a gestão de clientes e contas bancárias (Corrente e Poupança), lidando com operações financeiras críticas através de transações seguras.

A arquitetura foi desenhada para isolar regras de negócio na camada de `Service`, proteger a entrada de dados com o padrão `DTO` (Data Transfer Object), garantir a integridade da base de dados com o PostgreSQL usando `JPA/Hibernate` e proteger os endpoints com **Spring Security e Tokens JWT**.

## ✅ Funcionalidades

- **Segurança Avançada:** Autenticação e Autorização Stateless. Geração de tokens JWT e criptografia de senhas irreversíveis utilizando BCrypt.
- **Gestão de Contas:** Abertura de Contas Corrente e Poupança utilizando herança no mapeamento relacional (`@Inheritance(strategy = SINGLE_TABLE)`).
- **Operações Financeiras:** Depósitos e Saques com aplicação de regras de negócio (ex: taxas exclusivas para Conta Corrente).
- **Transferências:** Envio de valores entre contas com rollback automático em caso de falha (`@Transactional`).
- **Validação de Dados:** Filtro rigoroso nas requisições HTTP utilizando Bean Validation (`@NotNull`, `@Positive`) nos DTOs (Records).
- **Tratamento Global de Erros:** Respostas padronizadas para exceções da API utilizando `@RestControllerAdvice`.

##  Tecnologias e Padrões Utilizados

- **Linguagem:** Java 21
- **Framework:** Spring Boot (Web, Data JPA, Validation, Security)
- **Segurança:** Auth0 (Java JWT), BCryptPasswordEncoder
- **Base de Dados:** PostgreSQL
- **Ferramentas:** Lombok, Maven, Swagger (OpenAPI)
- **Arquitetura:** MVC / Layered Architecture / Fail Fast Validation

## 📚 Endpoints da API

Abaixo estão as principais rotas disponíveis para integração. *Nota: Com exceção do `/login` e do `/swagger-ui`, todas as rotas exigem o envio do cabeçalho `Authorization: Bearer <token>`.*

| Método | Endpoint | Descrição | Corpo da Requisição (JSON) |
|---|---|---|---|
| `POST` | `/login` | Autentica o usuário e devolve o Token JWT | `login`, `senha` |
| `POST` | `/contas` | Abre uma nova conta | `clienteId`, `numero`, `saldoInicial`, `tipoDeConta` ("CC" ou "CP") |
| `PUT` | `/contas/{id}/depositar` | Deposita um valor | `valor` |
| `PUT` | `/contas/{id}/sacar` | Saca um valor (aplica taxas) | `valor` |
| `PUT` | `/contas/{id}/transferir`| Transfere para outra conta | `contaDestinoId`, `valor` |

### Exemplo de requisição — `POST /login`

```json
{
  "login": "usuario@email.com",
  "senha": "minhaSenhaSegura123"
}
```

### Exemplo de resposta

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## ⚙️ Como executar o projeto localmente

### Pré-requisitos
- Java 21
- PostgreSQL em execução

### Passos

1. Crie uma base de dados no PostgreSQL (ex: `bytebank_db`).

2. Clone este repositório:
   ```bash
   git clone https://github.com/Davi-Silva-Developer/Bytebank-API-SpringBoot.git
   cd Bytebank-API-SpringBoot
   ```

3. Atualize o ficheiro `src/main/resources/application.properties` com as suas credenciais do PostgreSQL e a chave secreta da API:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/bytebank_db
   spring.datasource.username=SEU_USUARIO
   spring.datasource.password=SUA_SENHA

   api.security.token.secret=SUA_CHAVE_SECRETA_AQUI
   ```

4. Execute a aplicação através da sua IDE (IntelliJ/Eclipse) ou via Maven:
   ```bash
   mvn spring-boot:run
   ```

O servidor iniciará na porta `8080`.

Acesse a documentação interativa no navegador: `http://localhost:8080/swagger-ui.html`

## 🗺️ Roadmap de Evolução

Este projeto está em desenvolvimento contínuo, seguindo um roadmap estruturado para aplicar as melhores práticas de mercado:

- [x] **Fase 1:** Base Profissional (Spring Boot, JPA, Arquitetura MVC, DTOs e Bean Validation)
- [x] **Fase 2:** Tratamento de Erros e Documentação (`@RestControllerAdvice` + Swagger/OpenAPI)
- [x] **Fase 3:** Qualidade de Código (cobertura de testes com JUnit e Mockito)
- [x] **Fase 4:** Segurança (Spring Security, BCrypt e tokens JWT Stateless)
- [ ] **Fase 5:** Nuvem e Mensageria (containerização com Docker e simulação de microsserviços com filas Kafka/RabbitMQ)

## 👤 Autor

Desenvolvido com ☕ e dedicação por **Davi Silva**.
