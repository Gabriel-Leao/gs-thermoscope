# GS Queimadas - Thermoscope

## Descrição

## Visão Geral

API para gerenciamento de incidentes de queimadas, equipes de combate e áreas monitoradas. Desenvolvida em Java com
Spring Boot como trabalho acadêmico.

## Tecnologias Utilizadas

- **Java 21**: Linguagem principal do projeto.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Security**: Para configuração de segurança e autenticação.
- **H2 Database**: Banco de dados em memória utilizado para desenvolvimento e testes.
- **SpringDoc OpenAPI**: Documentação interativa da API (Swagger UI).
- **Lombok**: Geração de código boilerplate.
- **JPA (Jakarta Persistence API)**: Para mapeamento objeto-relacional (ORM).
- **Flyway**: Para migração de banco de dados.
- **Java-jwt**: Para criação e validaçã̀o do token jwt

## Estrutura do Projeto

- `entities/`: Classes que representam as tabelas do banco.
- `controllers/` Endpoints da API
- `services/` Regras de negócio da API
- `repositories/` Repositórios jpa das entidades
- `models/` Modelos de domínio que não são entidades
- `dtos/`: Objetos de transferência de dados.
- `configs/`: Configurações de segurança, banco e utilitários.
- `exceptions/`: Exceções customizadas e tratador global.
- `validations/`: Validações personalizadas para os dtos

## Documentação da API com Swagger

A documentação interativa da API está disponível através do SpringDoc OpenAPI (Swagger UI). Para acessar:

1. Execute a aplicação
2. Acesse: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Banco de Dados H2

O projeto utiliza o banco de dados H2 em memória para facilitar os testes. Para acessar o console do H2:

1. Execute a aplicação
2. Acesse: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
3. Credenciais:

- Datasource URL: `jdbc:h2:mem:thermoscope`
- User Name: `sa`
- Password: (deixe em branco)

## Inicialização do Banco de Dados

### Estrutura da Tabela

```sql
-- USERS
CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(100)        NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(20)                  DEFAULT 'FIREFIGHTER' NOT NULL,
    created_at TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- TEAMS
CREATE TABLE teams
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(50)  NOT NULL UNIQUE,
    location   VARCHAR(100) NOT NULL,
    status     VARCHAR(20)           DEFAULT 'AVAILABLE' NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- MONITORED AREAS
CREATE TABLE monitored_areas
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(50)                  NOT NULL,
    description VARCHAR(200),
    risk        VARCHAR(10) DEFAULT 'MEDIUM' NOT NULL
);

-- HEAT SOURCES
CREATE TABLE heat_sources
(
    id             UUID PRIMARY KEY,
    area_id        UUID                      NOT NULL,
    intensity      VARCHAR(10) DEFAULT 'LOW' NOT NULL,
    confirmed      CHAR(1)     DEFAULT 'N'   NOT NULL,
    detection_time TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (area_id) REFERENCES monitored_areas (id) ON DELETE CASCADE
);

-- INCIDENTS
CREATE TABLE incidents
(
    id              UUID PRIMARY KEY,
    heat_source_id  UUID                          NOT NULL,
    team_id         UUID                          NOT NULL,
    user_id         UUID                          NOT NULL,
    status          VARCHAR(20) DEFAULT 'PENDING' NOT NULL,
    response_time   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    conclusion_date DATE,
    report          VARCHAR(500),

    FOREIGN KEY (heat_source_id) REFERENCES heat_sources (id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- CONSTRAINTS
ALTER TABLE users
    ADD CONSTRAINT ck_user_role
        CHECK (role IN ('ADMIN', 'COORDINATOR', 'FIREFIGHTER'));

ALTER TABLE users
    ADD CONSTRAINT ck_password_length
        CHECK (LENGTH(password) >= 8);

ALTER TABLE teams
    ADD CONSTRAINT ck_team_status
        CHECK (status IN ('AVAILABLE', 'ON_MISSION', 'INACTIVE'));

ALTER TABLE monitored_areas
    ADD CONSTRAINT ck_area_risk
        CHECK (risk IN ('LOW', 'MEDIUM', 'HIGH'));

ALTER TABLE heat_sources
    ADD CONSTRAINT ck_heat_intensity
        CHECK (intensity IN ('LOW', 'MEDIUM', 'HIGH'));

ALTER TABLE heat_sources
    ADD CONSTRAINT ck_heat_confirmed
        CHECK (confirmed IN ('Y', 'N'));

ALTER TABLE incidents
    ADD CONSTRAINT ck_incident_status
        CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED'));

-- INDEXES
CREATE INDEX idx_heat_area ON heat_sources (area_id);
CREATE INDEX idx_heat_time ON heat_sources (detection_time);
CREATE INDEX idx_incident_heat ON incidents (heat_source_id);
CREATE INDEX idx_incident_team ON incidents (team_id);
CREATE INDEX idx_incident_status ON incidents (status);

```

## Como Executar

1. Clone o repositório:

```bash
  git clone https://github.com/Gabriel-Leao/gs-thermoscope.git
```

2. Navegue até o diretório do projeto:

```bash
  cd gs-thermoscope
```

3. Execute a aplicação com o Maven:

```bash
  mvn spring-boot:run
```

## Documentação

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Maven Documentation](https://maven.apache.org/)
- [Link para o vídeo apresentação](https://www.youtube.com/watch?v=CpUIVoMXoBQ)

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## Colaboradores do grupo

<div>
 <p>Vinicius Caetano - RM 552904</p>
 <p>Vinicius Issa Gois - RM 553814</p>
 <p>Gabriel Leão - RM 552642</p>
</div>
