CREATE TABLE adicional_produto (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(80),
  codigo_produto BIGINT(20),
  FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
  )
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;

INSERT INTO adicional_produto (nome, codigo_produto) VALUES ('Copo Limão e Gelo', 4);
INSERT INTO adicional_produto (nome, codigo_produto) VALUES ('Copo Limão e Gelo', 5);

INSERT INTO adicional_produto (nome, codigo_produto) VALUES ('Copo Limão e Gelo', 1);
INSERT INTO adicional_produto (nome, codigo_produto) VALUES ('Copo Limão e Gelo', 3);