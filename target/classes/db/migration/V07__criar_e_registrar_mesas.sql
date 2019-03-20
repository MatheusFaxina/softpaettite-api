CREATE TABLE mesa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
	ativo BIT(1) NOT NULL,
	em_uso BIT(1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 01', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 02', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 03', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 04', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 05', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 06', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 07', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 08', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 09', 1, 0);
INSERT INTO mesa (nome, ativo, em_uso) values ('Mesa 10', 1, 0);