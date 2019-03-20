CREATE TABLE sub_categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50),
	codigo_categoria BIGINT(20),
  FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sub_categoria (nome, codigo_categoria) values ('Lasanha', 1);
INSERT INTO sub_categoria (nome, codigo_categoria) values ('Cervejas', 2);
INSERT INTO sub_categoria (nome, codigo_categoria) values ('Destilados', 2);
INSERT INTO sub_categoria (nome, codigo_categoria) values ('Frutas', 4);
INSERT INTO sub_categoria (nome, codigo_categoria) values ('Refrigerantes', 5);
INSERT INTO sub_categoria (nome, codigo_categoria) values ('Salgada', 3);
INSERT INTO sub_categoria (nome, codigo_categoria) values ('Docês', 3);
INSERT INTO sub_categoria (nome, codigo_categoria) values ('Carnes', 1);
INSERT INTO sub_categoria (nome, codigo_categoria) values ('Porções', 1);