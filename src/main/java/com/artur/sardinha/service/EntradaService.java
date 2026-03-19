package com.artur.sardinha.service;

import com.artur.sardinha.model.Entrada;
import com.artur.sardinha.repository.EntradaRepository;

import java.util.List;

public class EntradaService {

    private EntradaRepository entradaRepository;

    public EntradaService() {
        this.entradaRepository = new EntradaRepository();
    }

    public void registrar(Entrada entrada) {
        entradaRepository.SalvarEntrada(entrada);
    }

    public List<Entrada> listarTodos() {
        return entradaRepository.buscarTodos();
    }

    public void deletar(int id) {
        entradaRepository.deletar(id);
    }
}