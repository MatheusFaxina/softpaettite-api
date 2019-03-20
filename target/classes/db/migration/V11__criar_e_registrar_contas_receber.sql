CREATE TABLE contas_receber (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_criacao DATE,
	data_vencimento DATE,
	data_pagamento DATE,
	valor_a_receber DECIMAL(10,2),
	valor_pago DECIMAL(10,2),
	valor_desconto DECIMAL(10,2),
	valor_juros DECIMAL(10,2),	
	codigo_venda BIGINT(20),
	FOREIGN KEY (codigo_venda) REFERENCES venda(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO contas_receber (data_criacao, data_vencimento, data_pagamento, valor_a_receber, valor_pago, valor_desconto, valor_juros, codigo_venda) 
values ('2017-06-10', '2017-06-10', '2017-06-10', 550.00, 530.00, 20.00, 0.00, 1);

INSERT INTO contas_receber (data_criacao, data_vencimento, data_pagamento, valor_a_receber, valor_pago, valor_desconto, valor_juros, codigo_venda) 
values ('2017-06-10', '2017-06-10', '2017-06-10', 550.00, 530.00, 20.00, 0.00, 1);