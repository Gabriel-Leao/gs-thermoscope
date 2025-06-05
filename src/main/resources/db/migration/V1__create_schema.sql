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
    id          UUID PRIMARY KEY,
    name        VARCHAR(50)                     NOT NULL UNIQUE,
    location    VARCHAR(100)                    NOT NULL,
    status      VARCHAR(20) DEFAULT 'AVAILABLE' NOT NULL,
    created_at  TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP
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
    id              UUID        PRIMARY KEY,
    heat_source_id  UUID        NOT NULL,
    team_id         UUID        NOT NULL,
    user_id         UUID        NOT NULL,
    status          VARCHAR(20) DEFAULT 'PENDING' NOT NULL,
    response_time   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    conclusion_date DATE,
    report          VARCHAR(500),

    FOREIGN KEY (heat_source_id) REFERENCES heat_sources    (id) ON DELETE CASCADE,
    FOREIGN KEY (team_id)        REFERENCES teams           (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id)        REFERENCES users           (id) ON DELETE CASCADE
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
