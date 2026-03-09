# Simple Product Catalog System

---

#  Product Catalog System (Microservices)

Este repositório contém um sistema de catálogo de produtos e gerenciamento de inventário baseado em uma arquitetura de microsserviços. O projeto utiliza **Java 21**, **Spring Boot 3.4** e **PostgreSQL** rodando em containers **Docker**. Projeto para o bootcamp DIO java backend em parceria com a Accenture

---

##  Estrutura do Projeto

O sistema é composto por dois serviços principais:

1.  **Product-Service**: Responsável pelo cadastro e consulta de produtos (Nome, Descrição, Preço).
2.  **Inventory-Service**: Responsável pelo controle de quantidade em estoque vinculado a cada produto.

###  Organização de Arquivos
```text
product-catalog-system/              # Raiz do Repositório (Projeto Multi-module)
├── docker-compose.yml               # Orquestração de Containers (Postgres 5432/5433)
│
├── product-Service/                 # Microsserviço A (Porta 8080)
│   ├── build.gradle                 # Configurações e Dependências do Gradle
│   └── src/main/
│       ├── java/br/com/dio/product/
│       │   ├── controller/          # Exposição dos Endpoints (ex: ProductController)
│       │   ├── dto/                 # Objetos de transferência (Data Transfer Objects)
│       │   ├── model/               # Entidades JPA (ex: Product.java)
│       │   ├── repository/          # Interfaces JpaRepository (Comunicação com DB)
│       │   └── service/             # Regras de Negócio (Lógica do sistema)
│       └── resources/
│           └── application.properties # Configurações (URL do Banco, Hibernate, Porta)
│
└── inventory-Service/               # Microsserviço B (Porta 8081)
    ├── build.gradle
    └── src/main/
        ├── java/br/com/dio/inventory/
        │   ├── client/              # Onde ficará o Feign Client (para chamar o Product-Service)
        │   ├── controller/          # Endpoints de estoque
        │   ├── model/               # Entidade Inventory (productId, quantity)
        │   └── repository/          # Acesso ao banco inventory_db
        └── resources/
            └── application.properties # Configurações específicas do inventário