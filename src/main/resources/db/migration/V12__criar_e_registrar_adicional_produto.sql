CREATE TABLE retirar_produto (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(80),
  codigo_produto BIGINT(20),
  FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
  )
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;

INSERT INTO retirar_produto (nome, codigo_produto) VALUES ('Cebola', 4);
INSERT INTO retirar_produto (nome, codigo_produto) VALUES ('Cebola', 5);

INSERT INTO retirar_produto (nome, codigo_produto) VALUES ('Cebola', 1);
INSERT INTO retirar_produto (nome, codigo_produto) VALUES ('Cebola', 3);