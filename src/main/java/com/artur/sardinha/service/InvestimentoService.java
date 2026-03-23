package com.artur.sardinha.service;

import com.artur.sardinha.model.Deposito;
import com.artur.sardinha.model.Investimento;
import com.artur.sardinha.repository.InvestimentoRepository;

import java.util.List;

public class InvestimentoService {

    private InvestimentoRepository investimentoRepository;

    public InvestimentoService() {
        this.investimentoRepository = new InvestimentoRepository();
    }

    public void registrar(Investimento investimento) {
        investimentoRepository.salvarInvestimento(investimento);
    }

    public List<Investimento> listarTodos() {
        return investimentoRepository.BuscarTodos();
    }

    public List<Deposito> listarDepositos(int investimentoId) {
        return investimentoRepository.buscarDepositos(investimentoId);
    }

    public void deletar(int id) {
        investimentoRepository.deletar(id);
    }
}