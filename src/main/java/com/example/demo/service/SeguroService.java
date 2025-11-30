package com.example.demo.service;

import com.example.demo.entity.Seguro;

import java.util.List;
import java.util.Optional;

// Contrato CRUD para polizas de seguro.
public interface SeguroService {

    Seguro crear(Seguro seguro);

    List<Seguro> listar();

    Optional<Seguro> obtenerPorId(String idSeguro);

    Seguro actualizar(String idSeguro, Seguro seguro);

    void eliminar(String idSeguro);

    List<Seguro> obtenerPorVehiculo(Long idVehiculo);
}
