CREATE TABLE parcela (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
	numero_parcela INT(11) NOT NULL,
	codigo_forma_pagamento BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_forma_pagamento) REFERENCES forma_pagamento(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO parcela (nome, numero_parcela, codigo_forma_pagamento) values ('30', 1, 1);
INSERT INTO parcela (nome, numero_parcela, codigo_forma_pagamento) values ('30 60', 2, 1);
INSERT INTO parcela (nome, numero_parcela, codigo_forma_pagamento) values ('30 60 90', 3, 1);
INSERT INTO parcela (nome, numero_parcela, codigo_forma_pagamento) values ('30 60 90 120', 4, 1);
INSERT INTO parcela (nome, numero_parcela, codigo_forma_pagamento) values ('30 60 90 120 150', 5, 1);
INSERT INTO parcela (nome, numero_parcela, codigo_forma_pagamento) values ('30 60 90 120 150 180', 6, 1);
INSERT INTO parcela (nome, numero_parcela, codigo_forma_pagamento) values ('30 60 90 120 150 180 210', 7, 1);