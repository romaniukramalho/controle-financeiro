package com.artur.sardinha.service;

import com.artur.sardinha.model.Gasto;
import com.artur.sardinha.model.Entrada;
import com.artur.sardinha.model.Investimento;
import com.artur.sardinha.repository.GastoRepository;
import com.artur.sardinha.repository.EntradaRepository;
import com.artur.sardinha.repository.InvestimentoRepository;
import com.artur.sardinha.model.Deposito;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioService
{
    private GastoRepository gastoRepository;
    private EntradaRepository entradaRepository;
    private InvestimentoRepository investimentoRepository;

    public RelatorioService() {
        this.gastoRepository = new GastoRepository();
        this.entradaRepository = new EntradaRepository();
        this.investimentoRepository = new InvestimentoRepository();
    }

    public BigDecimal calcularTotalGastos() {
        List<Gasto> gastos = gastoRepository.buscarTodos();
        BigDecimal total = BigDecimal.ZERO;

        for (Gasto gasto : gastos) {
            total = total.add(gasto.getValor());
        }

        return total;
    }

    public BigDecimal calcularTotalEntradas() {
        List<Entrada> entradas = entradaRepository.buscarTodos();
        BigDecimal total = BigDecimal.ZERO;

        for (Entrada entrada : entradas) {
            total = total.add(entrada.getValor());
        }

        return total;
    }

    public BigDecimal calcularTotalInvestimentos() {
        List<Investimento> investimentos = investimentoRepository.BuscarTodos();
        BigDecimal total = BigDecimal.ZERO;

        for (Investimento investimento : investimentos) {
            total = total.add(investimento.getValor());
        }

        return total;
    }

    public BigDecimal calcularSaldo() {
        BigDecimal totalEntradas = calcularTotalEntradas();
        BigDecimal totalGastos = calcularTotalGastos();

        return totalEntradas.subtract(totalGastos);
    }

    public BigDecimal calcularPatrimonio() {
        BigDecimal saldo = calcularSaldo();
        BigDecimal totalInvestimentos = calcularTotalInvestimentos();

        return saldo.add(totalInvestimentos);
    }

    public void exibirResumo() {
        System.out.println("\n===== RESUMO FINANCEIRO =====");
        System.out.println("Total de Entradas:     R$ " + calcularTotalEntradas());
        System.out.println("Total de Gastos:       R$ " + calcularTotalGastos());
        System.out.println("Total Investido:       R$ " + calcularTotalInvestimentos());
        System.out.println("Saldo:                 R$ " + calcularSaldo());
        System.out.println("Patrimônio Total:      R$ " + calcularPatrimonio());
        System.out.println("=============================\n");
    }
    public BigDecimal calcularTotalGastosPorMes(int mes, int ano) {
        List<Gasto> gastos = gastoRepository.buscarTodos();
        BigDecimal total = BigDecimal.ZERO;

        for (Gasto gasto : gastos) {
            if (gasto.getData().getMonthValue() == mes && gasto.getData().getYear() == ano) {
                total = total.add(gasto.getValor());
            }
        }
        return total;
    }

    public BigDecimal calcularTotalEntradasPorMes(int mes, int ano) {
        List<Entrada> entradas = entradaRepository.buscarTodos();
        BigDecimal total = BigDecimal.ZERO;

        for (Entrada entrada : entradas) {
            if (entrada.getData().getMonthValue() == mes && entrada.getData().getYear() == ano) {
                total = total.add(entrada.getValor());
            }
        }
        return total;
    }
    public void compararMeses(int mes1, int ano1, int mes2, int ano2) {
        BigDecimal entradasMes1 = calcularTotalEntradasPorMes(mes1, ano1);
        BigDecimal entradasMes2 = calcularTotalEntradasPorMes(mes2, ano2);
        BigDecimal gastosMes1 = calcularTotalGastosPorMes(mes1, ano1);
        BigDecimal gastosMes2 = calcularTotalGastosPorMes(mes2, ano2);
        BigDecimal saldoMes1 = entradasMes1.subtract(gastosMes1);
        BigDecimal saldoMes2 = entradasMes2.subtract(gastosMes2);
        BigDecimal aportadoMes1 = calcularTotalAportadoPorMes(mes1, ano1);
        BigDecimal aportadoMes2 = calcularTotalAportadoPorMes(mes2, ano2);


        System.out.println("\n===== COMPARATIVO MENSAL =====");
        System.out.println("Mês: " + mes1 + "/" + ano1 + " vs " + mes2 + "/" + ano2);
        System.out.println("------------------------------");
        System.out.println("Entradas: R$ " + entradasMes1 + "  →  R$ " + entradasMes2);
        System.out.println("Gastos:   R$ " + gastosMes1 + "  →  R$ " + gastosMes2);
        System.out.println("Saldo:    R$ " + saldoMes1 + "  →  R$ " + saldoMes2);
        System.out.println("Investido: R$ " + aportadoMes1 + "  →  R$ " + aportadoMes2);
        System.out.println("==============================\n");
    }
    public BigDecimal calcularTotalAportadoPorMes(int mes, int ano) {
        List<Investimento> investimentos = investimentoRepository.BuscarTodos();
        BigDecimal total = BigDecimal.ZERO;

        // busca os depósitos de cada investimento
        for (Investimento investimento : investimentos) {
            List<Deposito> depositos = investimentoRepository.buscarDepositos(investimento.getId());
            for (Deposito deposito : depositos) {
                if (deposito.getData().getMonthValue() == mes && deposito.getData().getYear() == ano) {
                    total = total.add(deposito.getValor());
                }
            }
        }
        return total;
    }
}
