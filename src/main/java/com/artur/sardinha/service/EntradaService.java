package com.artur.sardinha.service;

import com.artur.sardinha.model.Ganhos;
import com.artur.sardinha.repository.EntradaRepository;

import java.util.List;

public class EntradaService {

    private EntradaRepository entradaRepository;

    public EntradaService() {
        this.entradaRepository = new EntradaRepository();
    }

    public void registrar(Ganhos ganho) {
        entradaRepository.SalvarEntrada(ganho);
    }

    public List<Ganhos> listarTodos() {
        return entradaRepository.buscarTodos();
    }

    public void deletar(int id) {
        entradaRepository.deletar(id);
    }
}