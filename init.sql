CREATE TABLE IF NOT EXISTS gastos(
    id SERIAL PRIMARY KEY,
    valor DECIMAL(10,2) NOT NULL,
    descricao VARCHAR(255),
    data DATE NOT NULL,
    categoria VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS entradas (
    id SERIAL PRIMARY KEY,
    valor DECIMAL(10,2) NOT NULL,
    descricao VARCHAR(255),
    data DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS investimentos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    tipo_investimento VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS depositos (
    id SERIAL PRIMARY KEY,
    investimento_id INT REFERENCES investimentos(id) ON DELETE RESTRICT,
    valor DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL
);