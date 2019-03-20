CREATE TABLE observacao_produto (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(80),
  codigo_produto BIGINT(20),
  FOREIGN KEY (codigo_produto) REFERENCES produto(codigo))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;

INSERT INTO observacao_produto (nome, codigo_produto) VALUES ('Bem Passada', 4);
INSERT INTO observacao_produto (nome, codigo_produto) VALUES ('Bem Passada', 5);

INSERT INTO observacao_produto (nome, codigo_produto) VALUES ('Bem Passada', 1);
INSERT INTO observacao_produto (nome, codigo_produto) VALUES ('Bem Passada', 3);