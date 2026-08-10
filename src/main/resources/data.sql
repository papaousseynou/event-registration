-- Utilisateurs
INSERT INTO users (username, password, enabled) VALUES
    ('Souleymane', '$2b$12$v11FIQoILLHJCjNC/moNG.71BJYf6qO637O5lY3AUM9pEEoMCB.6i', TRUE),
    ('Najad', '$2b$12$mcnkOR2yn55HZx1tT7S1WOEW.ZL9IPXivRbiEiKBmobh488Viu70e', TRUE);

-- Rôles
INSERT INTO authorities (username, authority) VALUES
    ('Souleymane', 'ROLE_USER'),
    ('Najad', 'ROLE_ADMIN');

-- Événements
INSERT INTO evenement (nom, description, date_evenement, lieu) VALUES
    ('Conférence Tech 2026', 'Conférence annuelle sur les nouvelles technologies', '2026-09-15', 'Paris'),
    ('Workshop Spring Boot', 'Atelier pratique de développement avec Spring Boot', '2026-10-20', 'Lyon'),
    ('Hackathon Innovation', 'Compétition de 48h autour de l''innovation numérique', '2026-11-05', 'Marseille');
