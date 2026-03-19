package com.artur.sardinha.service;

import com.artur.sardinha.model.Gasto;
import com.artur.sardinha.repository.GastoRepository;

import java.util.List;

public class GastoService {

    private GastoRepository gastoRepository;

    public GastoService() {
        this.gastoRepository = new GastoRepository();
    }

    public void registrar(Gasto gasto) {
        gastoRepository.salvar(gasto);
    }

    public List<Gasto> listarTodos() {
        return gastoRepository.buscarTodos();
    }

    public void deletar(int id) {
        gastoRepository.deletar(id);
    }
}