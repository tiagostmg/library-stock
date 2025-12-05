# 📚 Library Stock --- Backend (Spring Boot)

Este repositório contém o backend do sistema **Library Stock**,
desenvolvido em **Java + Spring Boot**.\
O objetivo do projeto é gerenciar livros, categorias, usuários,
empréstimos e todo o fluxo de estoque de uma biblioteca.

> 🔗 **Frontend do projeto:**
> https://github.com/tiagostmg/frontend-library-stock

## Tecnologias Utilizadas

-   Java 17
-   Spring Boot
-   Spring Web
-   Spring Data JPA
-   Hibernate
-   PostgreSQL
-   Docker
-   Maven
-   Lombok

## 🗄️ Configuração do Banco de Dados (com Docker)

Para facilitar o setup do ambiente, o projeto utiliza **Docker** para subir o banco PostgreSQL.

### 🧱 Requisitos
- Docker
- Docker Compose

### ▶️ Subindo o Banco de Dados

Na raiz do projeto, execute:


    docker compose up -d

Isso irá iniciar um container com PostgreSQL configurado automaticamente pela definição do docker-compose.yml.

## Como Executar

    mvn spring-boot:run

API: http://localhost:8080

## Integração com o Frontend

🔗 https://github.com/tiagostmg/frontend-library-stock
