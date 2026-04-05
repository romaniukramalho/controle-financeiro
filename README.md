# 💰API de Sistema de Controle Financeiro

Sistema de controle financeiro desenvolvido em Java com arquitetura em camadas, banco de dados PostgreSQL e integração com APIs externas para cotação de ativos em tempo real.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17** — linguagem principal
- **PostgreSQL** — banco de dados relacional
- **JDBC** — conexão com o banco de dados
- **Jackson** — parsing de respostas JSON das APIs
- **Maven** — gerenciamento de dependências e build
- **Docker & Docker Compose** — containerização da aplicação e do banco
- **Git & GitHub** — versionamento

---

## ⚙️ Configuração

1. Clone o repositório
2. Crie um arquivo `.env` na raiz do projeto baseado no `.env.example`:
```
 DB_URL=jdbc:postgresql://localhost:5432/sardinha
 DB_USER=seu_usuario
 DB_PASSWORD=sua_senha
 ALPHA_VANTAGE_KEY=sua_chave
```
4. Para obter a API Key da Alpha Vantage acesse: https://www.alphavantage.co/support/#api-key

5. Execute com Docker:
```bash
docker-compose up --build
```

---

## 📁 Estrutura do Projeto

```
src/main/java/com/artur/sardinha/
├── model/
│   ├── Transactions.java        # Classe abstrata base
│   ├── Gasto.java               # Subclasse de gastos
│   ├── Entrada.java             # Subclasse de entradas
│   ├── Investimento.java        # Subclasse de investimentos
│   └── Deposito.java            # Histórico de aportes
├── enums/
│   ├── Categoria.java           # Categorias de gastos
│   ├── TipoTransaction.java     # Tipos de transação
│   └── TipoInvestimento.java    # Tipos de investimento
├── repository/
│   ├── GastoRepository.java
│   ├── EntradaRepository.java
│   └── InvestimentoRepository.java
├── service/
│   ├── DatabaseConnection.java  # Conexão com PostgreSQL
│   ├── GastoService.java
│   ├── EntradaService.java
│   ├── InvestimentoService.java
│   ├── RelatorioService.java    # Relatórios e comparativos
│   └── CotacaoService.java      # Integração com APIs externas
└── Main.java                    # Menu principal
```

---

## 🌐 APIs Utilizadas

| API | Finalidade | Documentação |
|---|---|---|
| **Alpha Vantage** | Cotação de ações em tempo real | [alphavantage.co](https://www.alphavantage.co) |
| **CoinGecko** | Cotação de criptomoedas | [coingecko.com](https://www.coingecko.com/en/api) |
| **Banco Central (BCB)** | Índices econômicos — SELIC, IPCA e CDI | [dadosabertos.bcb.gov.br](https://dadosabertos.bcb.gov.br) |

---

## 🗄️ Modelo do Banco de Dados

```
gastos
├── id, valor, descricao, data, categoria

entradas
├── id, valor, descricao, data

investimentos
├── id, nome, tipo_investimento

depositos
├── id, investimento_id (FK), valor, data
```

---

## ✨ Funcionalidades

- Registro e listagem de gastos, entradas e investimentos
- Histórico de aportes por investimento
- Cotação em tempo real de ações, criptomoedas e índices econômicos
- Relatório financeiro com saldo e patrimônio total
- Comparativo mensal de gastos, entradas e aportes
