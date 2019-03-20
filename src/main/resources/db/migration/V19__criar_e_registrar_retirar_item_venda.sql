CREATE TABLE retirar_item_venda (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  codigo_item_venda BIGINT(20) NOT NULL,
  codigo_retirar BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_item_venda) REFERENCES item_venda(codigo),
  FOREIGN KEY (codigo_retirar) REFERENCES retirar_produto(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO retirar_item_venda (codigo_item_venda, codigo_retirar) VALUES (12, 12);
INSERT INTO retirar_item_venda (codigo_item_venda, codigo_retirar) VALUES (12, 12);

INSERT INTO retirar_item_venda (codigo_item_venda, codigo_retirar) VALUES (12, 12);
INSERT INTO retirar_item_venda (codigo_item_venda, codigo_retirar) VALUES (12, 12);