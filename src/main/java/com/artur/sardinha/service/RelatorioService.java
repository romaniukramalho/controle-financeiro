package com.artur.sardinha.service;

import com.artur.sardinha.model.Gasto;
import com.artur.sardinha.model.Entrada;
import com.artur.sardinha.model.Investimento;
import com.artur.sardinha.repository.GastoRepository;
import com.artur.sardinha.repository.EntradaRepository;
import com.artur.sardinha.repository.InvestimentoRepository;

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
}
