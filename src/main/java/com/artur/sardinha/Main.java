package com.artur.sardinha;

import com.artur.sardinha.service.*;
import com.artur.sardinha.enums.Categoria;
import com.artur.sardinha.enums.TipoInvestimento;
import com.artur.sardinha.model.Gasto;
import com.artur.sardinha.model.Entrada;
import com.artur.sardinha.model.Investimento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static GastoService gastoService = new GastoService();
    static EntradaService entradaService = new EntradaService();
    static InvestimentoService investimentoService = new InvestimentoService();
    static RelatorioService relatorioService = new RelatorioService();
    static CotacaoService cotacaoService = new CotacaoService();


    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registrarGasto();
                case 2 -> registrarEntrada();
                case 3 -> registrarInvestimento();
                case 4 -> listarGastos();
                case 5 -> listarEntradas();
                case 6 -> listarInvestimentos();
                case 7 -> relatorioService.exibirResumo();
                case 8 -> deletarGasto();
                case 9 -> deletarEntrada();
                case 10 -> deletarInvestimento();
                case 11 -> consultarCotacao();
                case 12 -> consultarTesouroDireto();
                case 13 -> consultarCripto();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    static void exibirMenu() {
        System.out.println("\n===== CONTROLE FINANCEIRO =====");
        System.out.println("1. Registrar Gasto");
        System.out.println("2. Registrar Entrada");
        System.out.println("3. Registrar Investimento");
        System.out.println("4. Listar Gastos");
        System.out.println("5. Listar Entradas");
        System.out.println("6. Listar Investimentos");
        System.out.println("7. Exibir Resumo Financeiro");
        System.out.println("8. Deletar Gasto");
        System.out.println("9. Deletar Entrada");
        System.out.println("10. Deletar Investimento");
        System.out.println("11. Consultar preço de ação");
        System.out.println("12. Consultar Tesouro Direto");
        System.out.println("13. Consultar Criptomoeda");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    static void registrarGasto() {
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        System.out.println("Categoria:");
        System.out.println("1. ALIMENTO");
        System.out.println("2. MORADIA");
        System.out.println("3. LAZER");
        System.out.println("4. EMERGENCIA");
        System.out.println("5. TRANSPORTE");
        System.out.print("Escolha: ");
        int opcaoCategoria = scanner.nextInt();
        scanner.nextLine();

        Categoria categoria = switch (opcaoCategoria) {
            case 1 -> Categoria.ALIMENTO;
            case 2 -> Categoria.MORADIA;
            case 3 -> Categoria.LAZER;
            case 4 -> Categoria.EMERGENCIA;
            case 5 -> Categoria.TRANSPORTE;
            default -> Categoria.ALIMENTO;
        };

        Gasto gasto = new Gasto(0, valor, descricao, data, categoria);
        gastoService.registrar(gasto);
    }
    static void registrarEntrada() {
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        Entrada entrada = new Entrada(0, valor, descricao, data);
        entradaService.registrar(entrada);
    }
    static void registrarInvestimento()
    {
        System.out.print("Nome da ação (ex: PETR4.SAO): ");
        String desc = scanner.nextLine();

        System.out.print("Valor investido: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Data do investimento (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        System.out.println("Tipo de Investimento:");
        System.out.println("1. STOCKS");
        System.out.println("2. RENDA_FIXA");
        System.out.println("3. CRIPTO");
        System.out.print("Escolha: ");
        int opcaoTipo = scanner.nextInt();
        scanner.nextLine();

        TipoInvestimento tipo = switch (opcaoTipo) {
            case 1 -> TipoInvestimento.STOCKS;
            case 2 -> TipoInvestimento.RENDA_FIXA;
            case 3 -> TipoInvestimento.CRIPTO;
            default -> TipoInvestimento.RENDA_FIXA;
        };

        // rentabilidade e data de vencimento serão preenchidas via API depois
        BigDecimal rentabilidade = BigDecimal.ZERO;
        LocalDate dataVencimento = null;
        if (tipo == TipoInvestimento.RENDA_FIXA) {
            System.out.print("Nome do título (ex: Tesouro IPCA+ 2029): ");
            String nomeTitulo = scanner.nextLine();
            // aqui vou chamar a API do Tesouro Nacional futuramente
            // rentabilidade = CotacaoService.getRentabilidade(nomeTitulo);
            // dataVencimento = CotacaoService.getDataVencimento(nomeTitulo);
        }
        Investimento investimento = new Investimento(0, valor, desc, data, tipo, rentabilidade);
        investimentoService.registrar(investimento);
    }
    static void listarGastos() {
        List<Gasto> gastos = gastoService.listarTodos();

        if (gastos.isEmpty()) {
            System.out.println("Nenhum gasto encontrado!");
            return;
        }

        System.out.println("\n===== GASTOS =====");
        for (Gasto gasto : gastos) {
            System.out.println(gasto);
        }
    }
    static void listarEntradas() {
        List<Entrada> entradas = entradaService.listarTodos();

        if (entradas.isEmpty()) {
            System.out.println("Nenhuma entrada encontrada!");
            return;
        }

        System.out.println("\n===== ENTRADAS =====");
        for (Entrada entrada : entradas) {
            System.out.println(entrada);
        }
    }
    static void listarInvestimentos() {
        List<Investimento> investimentos = investimentoService.listarTodos();

        if (investimentos.isEmpty()) {
            System.out.println("Nenhum investimento encontrado!");
            return;
        }

        System.out.println("\n===== INVESTIMENTOS =====");
        for (Investimento investimento : investimentos) {
            System.out.println(investimento);

            switch (investimento.getTipoInvestimento()) {
                case STOCKS -> {
                    System.out.println("Buscando cotação da ação...");
                    cotacaoService.getCotacao(investimento.getDesc());
                }
                case CRIPTO -> {
                    System.out.println("Buscando cotação da cripto...");
                    cotacaoService.getCotacaoCripto(investimento.getDesc());
                }
                case RENDA_FIXA -> {
                    System.out.println("Buscando indicadores...");
                    cotacaoService.getDadosIndicadores("SELIC");
                }
            }
            System.out.println("---");
        }
    }
    static void deletarEntrada() {
        List<Entrada> entradas = entradaService.listarTodos();

        if (entradas.isEmpty()) {
            System.out.println("Nenhuma entrada encontrada!");
            return;
        }

        listarEntradas();
        System.out.print("Qual entrada você deseja deletar: ");
        int entradadeletada = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = entradas.stream().anyMatch(e -> e.getId() == entradadeletada);

        if (!encontrado) {
            System.out.println("Entrada inválida! ID não encontrado.");
            return;
        }

        entradaService.deletar(entradadeletada);
        System.out.println("Entrada deletada com sucesso!");
    }
    static void deletarInvestimento() {
        List<Investimento> investimentos = investimentoService.listarTodos();

        if (investimentos.isEmpty()) {
            System.out.println("Nenhum investimento encontrada!");
            return;
        }

        listarInvestimentosSemCotacao();
        System.out.print("Qual investimento você deseja deletar: ");
        int investimentodeletado = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = investimentos.stream().anyMatch(e -> e.getId() == investimentodeletado);

        if (!encontrado) {
            System.out.println("Investimento inválido! ID não encontrado.");
            return;
        }

        investimentoService.deletar(investimentodeletado);
        System.out.println("Investimento deletado com sucesso!");
    }
    static void deletarGasto() {
        List<Gasto> gastos = gastoService.listarTodos();

        if (gastos.isEmpty()) {
            System.out.println("Nenhum gasto encontrada!");
            return;
        }

        listarGastos();
        System.out.print("Qual gasto você deseja deletar: ");
        int gastodeletado = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = gastos.stream().anyMatch(e -> e.getId() == gastodeletado);

        if (!encontrado) {
            System.out.println("Gasto inválido! ID não encontrado.");
            return;
        }

        gastoService.deletar(gastodeletado);
        System.out.println("Gasto deletado com sucesso!");
    }
    static void consultarCotacao() {
        System.out.print("Digite o símbolo da ação (ex: PETR4.SAO, AAPL): ");
        String simbolo = scanner.nextLine();

        System.out.println("Buscando cotação...");
        BigDecimal preco = cotacaoService.getCotacao(simbolo);

        if (preco.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Não foi possível encontrar a cotação para: " + simbolo);
        } else {
            System.out.println("Preço atual de " + simbolo + ": R$ " + preco);
        }
    }
    static void listarInvestimentosSemCotacao() {
        List<Investimento> investimentos = investimentoService.listarTodos();

        if (investimentos.isEmpty()) {
            System.out.println("Nenhum investimento encontrado!");
            return;
        }

        System.out.println("\n===== INVESTIMENTOS =====");
        for (Investimento investimento : investimentos) {
            System.out.println(investimento);
        }
    }
    static void consultarTesouroDireto() {
        System.out.println("Escolha o indicador:");
        System.out.println("1. SELIC");
        System.out.println("2. IPCA");
        System.out.println("3. CDI");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        String indicador = switch (opcao) {
            case 1 -> "SELIC";
            case 2 -> "IPCA";
            case 3 -> "CDI";
            default -> null;
        };

        if (indicador == null) {
            System.out.println("Opção inválida!");
            return;
        }

        cotacaoService.getDadosIndicadores(indicador);
    }
    static void consultarCripto() {
        System.out.println("Digite o nome da criptomoeda:");
        System.out.println("Exemplos: bitcoin, ethereum, solana, cardano");
        System.out.print("Nome: ");
        String moeda = scanner.nextLine().toLowerCase();

        System.out.println("Buscando cotação...");
        cotacaoService.getCotacaoCripto(moeda);
    }
}