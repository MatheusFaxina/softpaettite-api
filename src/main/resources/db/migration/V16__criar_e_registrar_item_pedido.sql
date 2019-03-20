CREATE TABLE `item_venda` (
  `codigo` BIGINT(20) AUTO_INCREMENT,
  `quantidade` FLOAT NOT NULL,
  `valor_unitario` DECIMAL(10,2),
  `codigo_venda` BIGINT(20) NOT NULL,
  `codigo_produto` BIGINT(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `FK60ym08cfoysa17wrn1swyiuda` (`codigo_venda` ASC),
  INDEX `FKtk55mn6d6bvl5h0no5uagi3sf` (`codigo_produto` ASC),
  CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda`
    FOREIGN KEY (`codigo_venda`)
    REFERENCES `venda` (`codigo`),
  CONSTRAINT `FKtk55mn6d6bvl5h0no5uagi3sf`
    FOREIGN KEY (`codigo_produto`)
    REFERENCES `produto` (`codigo`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;

INSERT INTO item_venda (quantidade, valor_unitario, codigo_venda, codigo_produto) VALUES (1, 50.99, 1, 1);
INSERT INTO item_venda (quantidade, valor_unitario, codigo_venda, codigo_produto) VALUES (2, 50.99, 1, 2);

INSERT INTO item_venda (quantidade, valor_unitario, codigo_venda, codigo_produto) VALUES (4, 50.99, 2, 1);
INSERT INTO item_venda (quantidade, valor_unitario, codigo_venda, codigo_produto) VALUES (2, 50.99, 2, 2);