CREATE TABLE adicional_item_venda (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  codigo_adicional BIGINT(20) NOT NULL,
  codigo_item_venda BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_item_venda) REFERENCES item_venda(codigo),
  FOREIGN KEY (codigo_adicional) REFERENCES adicional_produto(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO adicional_item_venda (codigo_item_venda, codigo_adicional) VALUES (12, 12);
INSERT INTO adicional_item_venda (codigo_item_venda, codigo_adicional) VALUES (12, 12);

INSERT INTO adicional_item_venda (codigo_item_venda, codigo_adicional) VALUES (12, 12);
INSERT INTO adicional_item_venda (codigo_item_venda, codigo_adicional) VALUES (12, 12);