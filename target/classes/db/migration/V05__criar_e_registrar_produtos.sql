CREATE TABLE produto (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(80),
	valor DECIMAL(10,2),
	estoque INT,
	estoque_minimo INT,
	valor_custo DECIMAL(10,2),
	codigo_categoria BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Lasanha Bolonhesa', 50, 500, 5, 25, 1);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Carne Assada', 55.00, 200, 5, 25, 1);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Panqueca', 50, 500, 5, 25, 1);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Pizza Moda da Casa', 55.00, 200, 5, 25, 1);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Pizza Monte Castelo', 50, 500, 5, 25, 1);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Pizza Querência', 55.00, 200, 5, 25, 1);

INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Cerveja Sub Zero', 50, 300, 5, 25, 1);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Cerveja Brahma', 55.00, 200, 5, 25, 2);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Cerveja Antartica Original', 50, 300, 5, 25, 1);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Cerveja Heineken', 55.00, 200, 5, 25, 2);

INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Agua sem gás', 50, 500, 5, 25, 2);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Agua com gás', 50, 500, 5, 25, 2);

INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Suco de Laranja', 50, 300, 5, 25, 2);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Suco de Goiaba', 50, 300, 5, 25, 2);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Suco de Acerola', 50, 300, 5, 25, 2);
INSERT INTO produto (nome, valor, estoque, estoque_minimo, valor_custo, codigo_categoria) values ('Suco de Melancia', 50, 300, 5, 25, 2);