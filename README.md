# 📝 To-Do List API

> API REST para gestão de tarefas, desenvolvida com **Java e Spring Boot**, com foco em organização em camadas, persistência de dados e boas práticas na construção de APIs REST.

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0+-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

##  Sobre o Projeto

Este projeto é uma API REST para gerenciamento de tarefas (to-do list), permitindo que cada usuário crie, liste, atualize e remova suas próprias tarefas. A aplicação foi construída seguindo o padrão de arquitetura em camadas (`Controller`, `Service`, `Repository`), com persistência de dados via JPA/Hibernate e autenticação Basic Auth protegida por criptografia BCrypt.

##  Tecnologias

- **Linguagem:** Java 21
- **Framework:** Spring Boot (Web MVC, Data JPA)
- **Persistência:** Hibernate + MySQL
- **Segurança:** Basic Auth + BCrypt
- **Outros:** Lombok, Maven

##  Funcionalidades

- Cadastro de usuários
- Criação de tarefas
- Listagem de tarefas do usuário autenticado
- Atualização de tarefas
- Remoção de tarefas

##  Arquitetura e Conceitos Aplicados

- API REST
- Arquitetura em camadas (Controller, Service, Repository)
- Persistência com JPA / Hibernate
- Integração com banco de dados MySQL
- Autenticação Basic Auth com senhas criptografadas via BCrypt
- Uso de DTOs para entrada e saída de dados

## ⚙️ Como executar o projeto

### Pré-requisitos
- Java 21
- Maven
- MySQL em execução

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/Davi-Silva-Developer/To_Do_List.git
   cd To_Do_List
   ```

2. Configure o acesso ao banco de dados em `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/todolist_db
   spring.datasource.username=SEU_USUARIO
   spring.datasource.password=SUA_SENHA
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível em: `http://localhost:8080`

##  Autenticação

Os endpoints de tarefas exigem autenticação **Basic Auth**:

1. Crie um usuário via `POST /users`.
2. Envie usuário e senha no header `Authorization` (Basic Auth) em cada requisição às rotas de `/tasks`.

## 📚 Endpoints principais

| Método | Rota | Descrição | Autenticação |
|---|---|---|---|
| `POST` | `/users` | Cria um novo usuário | Não |
| `GET` | `/tasks/` | Lista as tarefas do usuário autenticado | Sim |
| `POST` | `/tasks/` | Cria uma nova tarefa | Sim |
| `PUT` | `/tasks/{id}` | Atualiza uma tarefa existente | Sim |
| `DELETE` | `/tasks/{id}` | Remove uma tarefa | Sim |

### Exemplo de requisição — `POST /tasks/`

```json
{
  "title": "Estudar Spring Boot",
  "descricao": "Rever anotações de JPA e validações",
  "hrInicio": "2024-05-10T08:00:00",
  "hrfim": "2024-05-10T12:00:00",
  "prioridade": "ALTA"
}
```

### Exemplo de resposta

```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "descricao": "Rever anotações de JPA e validações",
  "hrInicio": "2024-05-10T08:00:00",
  "hrfim": "2024-05-10T12:00:00",
  "prioridade": "ALTA",
  "concluida": false
}
```

##  Roadmap de Evolução

- [x] Fase 1: CRUD básico de tarefas com Spring Boot e JPA
- [x] Fase 2: Autenticação Basic Auth com BCrypt
- [ ] Fase 3: Tratamento global de erros com `@RestControllerAdvice`
- [ ] Fase 4: Documentação interativa com Swagger/OpenAPI
- [ ] Fase 5: Testes automatizados com JUnit e Mockito
- [ ] Fase 6: Migração para autenticação stateless com JWT

##  Autor

**Davi Silva**
[LinkedIn](https://www.linkedin.com/in/davi-silva-dev) · [GitHub](https://github.com/Davi-Silva-Developer)

---
*Projeto iniciado durante um curso de Java com Spring Boot, evoluído com camadas adicionais de autenticação e tratamento de erro.*
