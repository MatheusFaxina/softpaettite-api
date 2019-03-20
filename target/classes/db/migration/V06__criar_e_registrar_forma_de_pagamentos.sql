CREATE TABLE forma_pagamento (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO forma_pagamento (nome) values ('A Prazo');
INSERT INTO forma_pagamento (nome) values ('A Vista');
INSERT INTO forma_pagamento (nome) values ('Boleto');
INSERT INTO forma_pagamento (nome) values ('Cartão de crédito');