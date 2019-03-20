CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Pratos');
INSERT INTO categoria (nome) values ('Bebidas Alcólicas');
INSERT INTO categoria (nome) values ('Pizzas');
INSERT INTO categoria (nome) values ('Sucos');
INSERT INTO categoria (nome) values ('Refrigerantes');