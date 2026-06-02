CREATE DATABASE IF NOT EXISTS rpg_manager_db;
USE rpg_manager_db;

CREATE TABLE IF NOT EXISTS personajes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    clase VARCHAR(20) NOT NULL,
    nivel INT NOT NULL DEFAULT 1,
    fecha_creacion DATETIME NOT NULL
);

-- Registros de prueba iniciales obligatorios para la demo
INSERT INTO personajes (nombre, clase, nivel, fecha_creacion) VALUES ('Arthas', 'Guerrero', 80, NOW());
INSERT INTO personajes (nombre, clase, nivel, fecha_creacion) VALUES ('Jaina', 'Mago', 85, NOW());
INSERT INTO personajes (nombre, clase, nivel, fecha_creacion) VALUES ('Sylvanas', 'Arquero', 80, NOW());