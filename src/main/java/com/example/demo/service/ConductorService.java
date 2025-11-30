package com.example.demo.service;

import com.example.demo.entity.Conductor;

import java.util.List;
import java.util.Optional;

// Contrato CRUD para conductores.
public interface ConductorService {

    Conductor crear(Conductor conductor);

    List<Conductor> listar();

    Optional<Conductor> obtenerPorId(Long idConductor);

    Conductor actualizar(Long idConductor, Conductor conductor);

    void eliminar(Long idConductor);
}
