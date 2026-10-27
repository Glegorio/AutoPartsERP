# AutoParts ERP — Backend API

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](http://localhost:8080/swagger-ui.html)
[![Licença MIT](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)

API REST para um sistema de gestão empresarial voltado ao segmento de autopeças, desenvolvida com **Java**, **Spring Boot** e **MySQL**.

O projeto utiliza arquitetura em camadas, DTOs, validação de dados e documentação interativa com Swagger UI.

---

## Sumário

- [Sobre o projeto](#sobre-o-projeto)
- [Funcionalidades disponíveis](#funcionalidades-disponíveis)
- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Roadmap](#roadmap)
- [Endpoints](#endpoints)
- [Como executar](#como-executar)
- [Exemplos de uso](#exemplos-de-uso)
- [Validação manual](#validação-manual)
- [Autor](#autor)
- [Licença](#licença)

## Sobre o projeto

O **AutoParts ERP** tem como objetivo apoiar as operações do varejo automotivo, reunindo funcionalidades de catálogo de peças, controle de estoque, relacionamento comercial e vendas.

A API foi estruturada para permitir o consumo por aplicações web, mobile e desktop, mantendo a interface do usuário separada das regras de negócio e da persistência dos dados.

**O estágio atual contempla o módulo de Gestão de Peças e Catálogo.** Os demais módulos estão previstos no roadmap.

## Funcionalidades disponíveis

- Cadastro de peças.
- Listagem das peças cadastradas.
- Consulta de uma peça pelo identificador.
- Atualização completa dos dados de uma peça.
- Exclusão de peças do catálogo.
- Registro de marca, código de barras e descrição de modelos compatíveis.
- Cadastro de preços de custo e venda.
- Registro da quantidade em estoque no cadastro da peça.
- Validação dos dados recebidos pela API.
- Persistência dos dados em MySQL.
- Documentação e execução de requisições pelo Swagger UI.

> O registro da quantidade em estoque faz parte do cadastro atual. O controle de entradas, saídas e histórico de movimentações está previsto para o módulo de estoque.

## Arquitetura

A aplicação utiliza **arquitetura em camadas**, com separação de responsabilidades entre a exposição dos endpoints, as regras de negócio e o acesso ao banco de dados.

```mermaid
flowchart TD
    A["Cliente / Swagger UI"] -->|"HTTP / JSON"| B["Controller"]
    B -->|"Dados validados / DTOs"| C["Service"]
    C -->|"Operações de persistência"| D["Repository"]
    D -->|"JPA / Hibernate"| E[("MySQL")]
```

### Responsabilidades

| Componente | Responsabilidade |
| --- | --- |
| **Controller** | Receber requisições HTTP, acionar a validação dos dados de entrada e retornar respostas. |
| **Service** | Executar regras de negócio e definir os limites das transações. |
| **Repository** | Realizar consultas e operações de persistência com Spring Data JPA. |
| **Entity** | Representar as entidades persistidas e seus mapeamentos no banco de dados. |
| **DTO** | Definir os dados utilizados nas requisições e respostas da API. |

### Práticas aplicadas

- **Separação de responsabilidades:** organização do código por camadas.
- **DTOs:** definição dos contratos de entrada e saída, reduzindo a exposição direta das entidades.
- **Bean Validation:** verificação de restrições nos dados recebidos, conforme as anotações configuradas.
- **Transações:** uso de `@Transactional` nas operações que exigem controle transacional.
- **Documentação interativa:** descrição e execução dos endpoints com Springdoc OpenAPI e Swagger UI.

> A validação verifica restrições dos dados. Ela não substitui a sanitização de conteúdo quando essa proteção for necessária.

## Tecnologias

| Categoria | Tecnologia | Versão informada |
| --- | --- | --- |
| Linguagem | Java | 17+ |
| Framework | Spring Boot | 3.x |
| Acesso a dados | Spring Data JPA | Gerenciada pelo Spring Boot |
| ORM | Hibernate ORM | Gerenciada pelo Spring Boot |
| Banco de dados | MySQL | 8.0+ |
| Validação | Jakarta Bean Validation | Gerenciada pelo Spring Boot |
| Documentação | Springdoc OpenAPI / Swagger UI | 2.5.0 |
| Build e dependências | Apache Maven | 3.8+ |

> Consulte o `pom.xml` para confirmar as versões efetivamente utilizadas pelo projeto.

## Roadmap

- [x] **Módulo 1 — Gestão de Peças e Catálogo**
    - CRUD de peças, DTOs, validações e persistência em MySQL.
- [X] **Módulo 2 — Controle de Estoque e Movimentações**
    - Entradas, saídas, histórico de movimentações e alertas de estoque mínimo.
- [ ] **Módulo 3 — Gestão de Clientes e Fornecedores**
    - Cadastros e vínculos comerciais.
- [ ] **Módulo 4 — Vendas e Pedidos**
    - Itens de venda, fechamento de pedidos e regras de desconto.
- [ ] **Módulo 5 — Dashboard e Relatórios**
    - Indicadores de vendas, relatórios financeiros e curva ABC de estoque.

## Endpoints

**URL base do ambiente local:**

```text
http://localhost:8080
```

### Peças

| Método | Rota | Descrição | Resposta de sucesso |
| --- | --- | --- | --- |
| `POST` | `/api/pecas` | Cadastrar uma peça. | `201 Created` |
| `GET` | `/api/pecas` | Listar as peças cadastradas. | `200 OK` |
| `GET` | `/api/pecas/{id}` | Consultar uma peça pelo ID. | `200 OK` |
| `PUT` | `/api/pecas/{id}` | Atualizar todos os dados de uma peça. | `200 OK` |
| `DELETE` | `/api/pecas/{id}` | Excluir uma peça pelo ID. | `204 No Content` |

> O endpoint `PUT` representa uma atualização completa. Envie todos os campos exigidos pelo DTO de atualização.

Os schemas e as restrições de cada operação podem ser consultados na documentação interativa.

## Como executar

### 1. Pré-requisitos

Instale e configure:

- **JDK 17** ou versão compatível com o `pom.xml`.
- **MySQL Server 8.0+**.
- **Git**.
- **Maven 3.8+**, caso o repositório não contenha o Maven Wrapper.
- Uma IDE de sua preferência, como IntelliJ IDEA, Eclipse ou VS Code.

Verifique a instalação do Java:

```bash
java -version
```

Caso utilize o Maven instalado na máquina:

```bash
mvn -version
```

### 2. Clonar o repositório

Substitua `SEU_USUARIO` pelo usuário ou organização responsável pelo repositório:

```bash
git clone https://github.com/SEU_USUARIO/erp-auto-parts.git
cd erp-auto-parts
```

### 3. Criar o banco de dados

No MySQL Workbench, DBeaver ou terminal do MySQL, execute:

```sql
CREATE DATABASE IF NOT EXISTS auto_pecas_db
    CHARACTER SET utf8mb4;
```

Certifique-se de que o usuário utilizado pela aplicação tenha permissão para acessar e alterar esse banco no ambiente local.

### 4. Configurar a aplicação

Crie ou atualize o arquivo:

```text
src/main/resources/application.properties
```

Adicione as configurações:

```properties
# Identificação da aplicação
spring.application.name=erp-auto-parts

# Servidor HTTP
server.port=8080

# Conexão com o banco de dados
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/auto_pecas_db?serverTimezone=UTC}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# JPA / Hibernate — desenvolvimento local
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Documentação
springdoc.swagger-ui.path=/swagger-ui.html
```

Configure as variáveis de ambiente antes de iniciar a aplicação:

| Variável | Descrição | Obrigatória |
| --- | --- | --- |
| `DB_USERNAME` | Usuário do MySQL. | Sim |
| `DB_PASSWORD` | Senha do usuário do MySQL. | Sim |
| `DB_URL` | URL JDBC do banco de dados. | Não; utiliza a conexão local configurada como padrão. |

#### IntelliJ IDEA

Acesse:

**Run → Edit Configurations → configuração da aplicação → Environment variables**

Cadastre `DB_USERNAME` e `DB_PASSWORD` com as credenciais do seu ambiente.

> Não versione senhas no repositório. A configuração acima utiliza variáveis do sistema operacional ou da configuração de execução da IDE; um arquivo `.env` não é carregado automaticamente por ela.

> `spring.jpa.hibernate.ddl-auto=update` é utilizado aqui para facilitar o desenvolvimento local. Para ambientes compartilhados ou de produção, adote migrações versionadas com ferramentas como Flyway ou Liquibase.

### 5. Executar a aplicação

Com o MySQL em execução e as variáveis de ambiente configuradas, utilize uma das opções abaixo.

#### Linux / macOS — Maven Wrapper

```bash
./mvnw spring-boot:run
```

#### Windows PowerShell — Maven Wrapper

```powershell
.\mvnw.cmd spring-boot:run
```

#### Maven instalado

```bash
mvn spring-boot:run
```

Também é possível executar a classe principal `ErpAutoPartsApplication.java` pela IDE.

> Os comandos do Maven Wrapper dependem da presença de `mvnw`, `mvnw.cmd` e dos arquivos de configuração do Wrapper no repositório.

### 6. Acessar a documentação

Com a aplicação iniciada, acesse:

| Recurso | Endereço |
| --- | --- |
| Swagger UI | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| Especificação OpenAPI | [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) |

## Exemplos de uso

### Cadastrar uma peça

**Requisição:**

```http
POST /api/pecas
Content-Type: application/json
```

```json
{
  "nome": "Pastilha de Freio Dianteira",
  "codigoBarra": "7891234567890",
  "marca": "Fras-le",
  "modeloCompativel": "Gol G5 / Voyage 1.6 2010+",
  "precoCusto": 45.50,
  "precoVenda": 89.90,
  "quantidadeEstoque": 25
}
```

**Exemplo de resposta — `201 Created`:**

```json
{
  "id": 1,
  "nome": "Pastilha de Freio Dianteira",
  "codigoBarra": "7891234567890",
  "marca": "Fras-le",
  "modeloCompativel": "Gol G5 / Voyage 1.6 2010+",
  "precoCusto": 45.50,
  "precoVenda": 89.90,
  "quantidadeEstoque": 25
}
```

> O identificador é gerado pelo banco de dados e pode ser diferente do exemplo.

### Consultar uma peça

```http
GET /api/pecas/1
```

### Atualizar uma peça

**Requisição:**

```http
PUT /api/pecas/1
Content-Type: application/json
```

```json
{
  "nome": "Pastilha de Freio Dianteira",
  "codigoBarra": "7891234567890",
  "marca": "Fras-le",
  "modeloCompativel": "Gol G5 / Voyage 1.6 2010+",
  "precoCusto": 48.00,
  "precoVenda": 94.90,
  "quantidadeEstoque": 30
}
```

**Resposta de sucesso:** `200 OK`.

### Excluir uma peça

```http
DELETE /api/pecas/1
```

**Resposta de sucesso:** `204 No Content`, sem corpo de resposta.

## Validação manual

Utilize o Swagger UI para verificar o fluxo principal:

1. Cadastre uma peça com `POST /api/pecas`.
2. Anote o `id` retornado.
3. Consulte a listagem com `GET /api/pecas`.
4. Consulte o registro com `GET /api/pecas/{id}`.
5. Atualize o cadastro com `PUT /api/pecas/{id}`, enviando todos os campos exigidos.
6. Consulte novamente para conferir a persistência das alterações.
7. Exclua o registro com `DELETE /api/pecas/{id}`.
8. Confira se o registro deixou de aparecer na listagem.

Verifique também as restrições declaradas nos DTOs, enviando valores inválidos e conferindo as respostas da API.

## Licença

Este projeto está sob a licença **MIT**.

Consulte o arquivo [LICENSE](LICENSE) para conhecer os termos de uso, cópia, modificação e distribuição.