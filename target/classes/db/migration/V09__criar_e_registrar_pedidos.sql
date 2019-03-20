CREATE TABLE venda (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cadastro DATE,
	codigo_mesa BIGINT(20) NOT NULL,
	valor_total DECIMAL(10,2),
	status VARCHAR(30),
	FOREIGN KEY (codigo_mesa) REFERENCES mesa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO venda (cadastro, codigo_mesa, valor_total, status) 
values ('2010-06-10', 4, 550.99, 'EM_ABERTO');

INSERT INTO venda (cadastro, codigo_mesa, valor_total, status) 
values ('2011-06-10', 1, 505.99, 'EM_ABERTO');

INSERT INTO venda (cadastro, codigo_mesa, valor_total, status) 
values ('2012-06-10', 1, 505.99, 'EM_ABERTO');

INSERT INTO venda (cadastro, codigo_mesa, valor_total, status) 
values ('2018-12-10', 1, 505.99, 'EM_ABERTO');

INSERT INTO venda (cadastro, codigo_mesa, valor_total, status) 
values ('2019-11-10', 1, 505.99, 'EM_ABERTO');

INSERT INTO venda (cadastro, codigo_mesa, valor_total, status) 
values ('2018-03-10', 1, 505.99, 'EM_ABERTO');

INSERT INTO venda (cadastro, codigo_mesa, valor_total, status) 
values ('2017-01-10', 1, 505.99, 'EM_ABERTO');

INSERT INTO venda (cadastro, codigo_mesa, valor_total, status) 
values ('2019-02-10', 1, 505.99, 'EM_ABERTO');