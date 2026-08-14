CREATE TABLE IF NOT EXISTS evenement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(200) NOT NULL,
    description VARCHAR(500),
    date_evenement DATE NOT NULL,
    lieu VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS inscription (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    prenom VARCHAR(100) NOT NULL,
    nom VARCHAR(100) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    date_naissance DATE NOT NULL,
    sexe VARCHAR(10) NOT NULL,
    evenement_id BIGINT NOT NULL,
    date_inscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (evenement_id) REFERENCES evenement(id)
);
