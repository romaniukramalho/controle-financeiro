package com.artur.sardinha;

import com.artur.sardinha.service.GastoService;
import com.artur.sardinha.service.EntradaService;
import com.artur.sardinha.service.InvestimentoService;
import com.artur.sardinha.service.RelatorioService;
import com.artur.sardinha.enums.Categoria;
import com.artur.sardinha.enums.TipoInvestimento;
import com.artur.sardinha.model.Gasto;
import com.artur.sardinha.model.Ganhos;
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

        System.out.print("Fonte (ex: salário, freelance): ");
        String fonte = scanner.nextLine();

        Ganhos ganho = new Ganhos(0, valor, descricao, data);
        entradaService.registrar(ganho);
    }
    static void registrarInvestimento()
    {
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

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

        // rentabilidade e data de vencimento serão preenchidas via API posteriormente
        BigDecimal rentabilidade = BigDecimal.ZERO;
        LocalDate dataVencimento = null;

        if (tipo == TipoInvestimento.RENDA_FIXA) {
            System.out.print("Nome do título (ex: Tesouro IPCA+ 2029): ");
            String nomeTitulo = scanner.nextLine();
            // aqui chamaremos a API do Tesouro Nacional futuramente
            // rentabilidade = CotacaoService.getRentabilidade(nomeTitulo);
            // dataVencimento = CotacaoService.getDataVencimento(nomeTitulo);
        }
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
        List<Ganhos> entradas = entradaService.listarTodos();

        if (entradas.isEmpty()) {
            System.out.println("Nenhuma entrada encontrada!");
            return;
        }

        System.out.println("\n===== ENTRADAS =====");
        for (Ganhos entrada : entradas) {
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
        }
    }
}