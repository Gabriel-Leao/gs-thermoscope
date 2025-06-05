-- 1. USERS
INSERT INTO users (id, name, email, password, role) VALUES
(RANDOM_UUID(), 'Admin Principal', 'admin@queimadas.com', 'Adm@12345', 'ADMIN'),
(RANDOM_UUID(), 'Coordenador Rio', 'coord.rio@queimadas.com', 'Rio@2023', 'COORDINATOR'),
(RANDOM_UUID(), 'Brigadista Norte', 'brig.norte@queimadas.com', 'Nort@5678', 'FIREFIGHTER'),
(RANDOM_UUID(), 'Brigadista Sul', 'brig.sul@queimadas.com', 'Sul@9101', 'FIREFIGHTER'),
(RANDOM_UUID(), 'Coordenador Geral', 'coord.geral@queimadas.com', 'Gera@2023', 'COORDINATOR');

-- 2. TEAMS
INSERT INTO teams (id, name, location, status) VALUES
(RANDOM_UUID(), 'TÁTICA ALFA', 'NORTE', 'AVAILABLE'),
(RANDOM_UUID(), 'TÁTICA BRAVO', 'SUL', 'ON_MISSION'),
(RANDOM_UUID(), 'TÁTICA CHARLIE', 'LESTE', 'AVAILABLE'),
(RANDOM_UUID(), 'TÁTICA DELTA', 'OESTE', 'INACTIVE'),
(RANDOM_UUID(), 'TÁTICA ECO', 'CENTRO', 'AVAILABLE');

-- 3. MONITORED AREAS
INSERT INTO monitored_areas (id, name, description, risk) VALUES
(RANDOM_UUID(), 'PARQUE CENTRAL', 'Área verde urbana', 'HIGH'),
(RANDOM_UUID(), 'RESERVA BIOLÓGICA', 'Proteção integral', 'MEDIUM'),
(RANDOM_UUID(), 'FAZENDA SÃO JORGE', 'Cultivo de cana', 'LOW'),
(RANDOM_UUID(), 'MATA ATLÂNTICA', 'Fragmento florestal', 'HIGH'),
(RANDOM_UUID(), 'VALE DO SOL', 'Área de transição', 'MEDIUM');

-- Para inserções seguintes, IDs devem ser capturados. Exemplo para simulação abaixo:
-- (Idealmente você usaria INSERTs com retorno de chave ou armazenaria UUIDs em variáveis se possível)

-- Exemplo ilustrativo: pegando os primeiros registros como base
-- Adapte conforme sua linguagem/framework de aplicação

-- 4. HEAT SOURCES
INSERT INTO heat_sources (id, area_id, intensity, confirmed) VALUES
(RANDOM_UUID(), (SELECT id FROM monitored_areas WHERE name = 'PARQUE CENTRAL'), 'HIGH', 'Y'),
(RANDOM_UUID(), (SELECT id FROM monitored_areas WHERE name = 'RESERVA BIOLÓGICA'), 'MEDIUM', 'N'),
(RANDOM_UUID(), (SELECT id FROM monitored_areas WHERE name = 'FAZENDA SÃO JORGE'), 'LOW', 'N'),
(RANDOM_UUID(), (SELECT id FROM monitored_areas WHERE name = 'MATA ATLÂNTICA'), 'HIGH', 'Y'),
(RANDOM_UUID(), (SELECT id FROM monitored_areas WHERE name = 'VALE DO SOL'), 'MEDIUM', 'Y');

-- 5. INCIDENTS
-- Adaptado com SELECTs para pegar os IDs
INSERT INTO incidents (id, heat_source_id, team_id, user_id, status, response_time, conclusion_date, report) VALUES
 (
    RANDOM_UUID(),
    (SELECT id FROM heat_sources LIMIT 1 OFFSET 0),
    (SELECT id FROM teams WHERE name = 'TÁTICA ALFA'),
    (SELECT id FROM users WHERE name = 'Brigadista Norte'),
    'COMPLETED',
    CURRENT_DATE - INTERVAL '5' DAY,
    CURRENT_DATE - INTERVAL '4' DAY,
    'Fogo controlado com 2 viaturas'
),
(
    RANDOM_UUID(),
    (SELECT id FROM heat_sources LIMIT 1 OFFSET 1),
    (SELECT id FROM teams WHERE name = 'TÁTICA BRAVO'),
    (SELECT id FROM users WHERE name = 'Coordenador Rio'),
    'COMPLETED',
    CURRENT_DATE - INTERVAL '4' DAY,
    CURRENT_DATE - INTERVAL '3' DAY,
    'Monitoramento preventivo'
),
(
    RANDOM_UUID(),
    (SELECT id FROM heat_sources LIMIT 1 OFFSET 2),
    (SELECT id FROM teams WHERE name = 'TÁTICA CHARLIE'),
    (SELECT id FROM users WHERE name = 'Brigadista Sul'),
    'IN_PROGRESS',
    CURRENT_DATE - INTERVAL '2' DAY,
    NULL,
    'Em avaliação'
),
(
    RANDOM_UUID(),
    (SELECT id FROM heat_sources LIMIT 1 OFFSET 3),
    (SELECT id FROM teams WHERE name = 'TÁTICA ECO'),
    (SELECT id FROM users WHERE name = 'Coordenador Geral'),
    'PENDING',
    CURRENT_DATE - INTERVAL '1' DAY,
    NULL,
    NULL
),
(
    RANDOM_UUID(),
    (SELECT id FROM heat_sources LIMIT 1 OFFSET 4),
    (SELECT id FROM teams WHERE name = 'TÁTICA ALFA'),
    (SELECT id FROM users WHERE name = 'Admin Principal'),
    'IN_PROGRESS',
    CURRENT_DATE,
    NULL,
    'Combate iniciado'
);
