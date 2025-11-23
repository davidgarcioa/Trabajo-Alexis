package com.example.demo.service;

import com.example.demo.entity.Vehiculo;

import java.util.List;
import java.util.Optional;

// Contrato de operaciones CRUD sobre vehiculos.
public interface VehiculoService {

    // Crea un vehiculo y valida que la placa no este repetida.
    Vehiculo crear(Vehiculo vehiculo);

    // Retorna todos los vehiculos almacenados.
    List<Vehiculo> listar();

    // Busca un vehiculo por id si existe.
    Optional<Vehiculo> obtenerPorId(Long idVehiculo);

    // Actualiza los datos del vehiculo indicado.
    Vehiculo actualizar(Long idVehiculo, Vehiculo vehiculo);

    // Elimina el vehiculo indicado.
    void eliminar(Long idVehiculo);
}
