CREATE TABLE demand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    author VARCHAR(255) NOT NULL
);

INSERT INTO demand (name, description, author) VALUES 
('Demande 1', 'Première description', 'Auteur1'),
('Demande 2', 'Deuxième description', 'Auteur2'),
('Demande 3', 'Troisième description', 'Auteur3');