#  Bytebank API - Spring Boot

> Uma API RESTful robusta para um sistema bancário digital, construída com Spring Boot, aplicando boas práticas de arquitetura em camadas, encapsulamento e persistência de dados.

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0+-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

##  Sobre o Projeto

Este projeto representa a evolução de um sistema bancário em Java puro para uma **API REST moderna e escalável**. Ele faz a gestão de clientes e contas bancárias (Corrente e Poupança), lidando com operações financeiras críticas através de transações seguras.

A arquitetura foi desenhada para isolar regras de negócio na camada de `Service`, proteger a entrada de dados com o padrão `DTO` (Data Transfer Object) e garantir a integridade da base de dados com o PostgreSQL usando `JPA/Hibernate`.

## ⚙️ Funcionalidades

- **Gestão de Contas:** Abertura de Contas Corrente e Poupança utilizando herança no mapeamento relacional (`@Inheritance(strategy = SINGLE_TABLE)`).
- **Operações Financeiras:** Depósitos e Saques com aplicação de regras de negócio (ex: taxas exclusivas para Conta Corrente).
- **Transferências:** Envio de valores entre contas com rollback automático em caso de falha (`@Transactional`).
- **Validação de Dados:** Filtro rigoroso nas requisições HTTP utilizando Bean Validation (`@NotNull`, `@Positive`) nos DTOs (Records).

## 🛠️ Tecnologias e Padrões Utilizados

- **Linguagem:** Java 21
- **Framework:** Spring Boot (Web, Data JPA, Validation)
- **Base de Dados:** PostgreSQL
- **Ferramentas:** Lombok (Redução de Boilerplate), Maven, Postman
- **Arquitetura:** MVC / Layered Architecture (Controller, Service, Repository, Model)

##  Endpoints da API

Abaixo estão as rotas disponíveis para integração:

| Método | Endpoint | Descrição | Corpo da Requisição (JSON) |
|---|---|---|---|
| `POST` | `/contas` | Abre uma nova conta | `clienteId`, `numero`, `saldoInicial`, `tipoDeConta` ("CC" ou "CP") |
| `PUT` | `/contas/{id}/depositar` | Deposita um valor | `valor` |
| `PUT` | `/contas/{id}/sacar` | Saca um valor (aplica taxas) | `valor` |
| `PUT` | `/contas/{id}/transferir`| Transfere para outra conta | `contaDestinoId`, `valor` |

##  Como executar o projeto localmente

1. Certifica-te de que tens o **Java 21** e o **PostgreSQL** instalados na sua máquina.
2. Cria uma base de dados no PostgreSQL (ex: `bytebank_db`).
3. Clona este repositório:
   ```bash
   git clone [https://github.com/Davi-Silva-Developer/Bytebank-API-SpringBoot.git](https://github.com/Davi-Silva-Developer/Bytebank-API-SpringBoot.git)
Atualiza o ficheiro src/main/resources/application.properties com as tuas credenciais do PostgreSQL:

Properties
- spring.datasource.url=jdbc:postgresql://localhost:5432/bytebank_db
- spring.datasource.username=TEU_USUARIO
- spring.datasource.password=TUA_SENHA
- Executa a aplicação através da tua IDE (IntelliJ/Eclipse) ou via Maven. O servidor iniciará na porta 8080.

🗺️ Roadmap de Evolução (Jornada Jr ao Pleno)
Este projeto está em desenvolvimento contínuo, seguindo um roadmap estruturado para aplicar as melhores práticas de mercado:

[x] Fase 1: Base Profissional (Spring Boot, JPA, Arquitetura MVC, DTOs e Bean Validation).

[ ] Fase 2: Tratamento de Erros e Documentação (Implementação de @ControllerAdvice para erros padronizados e Swagger/OpenAPI para documentação interativa).

[ ] Fase 3: Qualidade de Código (Cobertura de testes automatizados unitários e de integração com JUnit e Mockito).

[ ] Fase 4: Segurança (Autenticação e Autorização blindadas usando Spring Security e tokens JWT).

[ ] Fase 5: Nuvem e Mensageria (Containerização com Docker e simulação de microsserviços com filas Kafka/RabbitMQ).

Desenvolvido com ☕ e dedicação por Davi Silva.


### Para enviar para o GitHub:
Abre o terminal no IntelliJ e cola estes três comandos, um de cada vez:
```bash
git add README.md
git commit -m "docs: adiciona README completo com roadmap"
git push
