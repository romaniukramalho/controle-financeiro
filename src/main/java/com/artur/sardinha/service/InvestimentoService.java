package com.artur.sardinha.service;

import com.artur.sardinha.model.Investimento;
import com.artur.sardinha.repository.InvestimentoRepository;

import java.util.List;

public class InvestimentoService {

    private InvestimentoRepository investimentoRepository;

    public InvestimentoService() {
        this.investimentoRepository = new InvestimentoRepository();
    }

    public void registrar(Investimento investimento) {
        investimentoRepository.SalvarInvestimento(investimento);
    }

    public List<Investimento> listarTodos() {
        return investimentoRepository.BuscarTodos();
    }

    public void deletar(int id) {
        investimentoRepository.deletar(id);
    }
}