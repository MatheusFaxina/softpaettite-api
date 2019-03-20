CREATE TABLE observacao_item_venda (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  codigo_item_venda BIGINT(20) NOT NULL,
  codigo_observacao BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_item_venda) REFERENCES item_venda(codigo),
  FOREIGN KEY (codigo_observacao) REFERENCES observacao_produto(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO observacao_item_venda (codigo_item_venda, codigo_observacao) VALUES (12, 12);
INSERT INTO observacao_item_venda (codigo_item_venda, codigo_observacao) VALUES (12, 12);

INSERT INTO observacao_item_venda (codigo_item_venda, codigo_observacao) VALUES (12, 12);
INSERT INTO observacao_item_venda (codigo_item_venda, codigo_observacao) VALUES (12, 12);