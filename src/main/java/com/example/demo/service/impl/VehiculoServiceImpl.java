package com.example.demo.service.impl;

import com.example.demo.entity.Conductor;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repository.ConductorRepository;
import com.example.demo.repository.VehiculoRepository;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// Implementacion de la logica de negocio para CRUD de vehiculos.
@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public Vehiculo crear(Vehiculo vehiculo) {
        // Valida que la placa no exista antes de crear el registro.
        if (vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La placa ya esta registrada");
        }
        // Si viene conductor, valida que exista en BD antes de asociarlo.
        if (vehiculo.getConductor() != null && vehiculo.getConductor().getIdConductor() != null) {
            Conductor conductor = conductorRepository.findById(vehiculo.getConductor().getIdConductor())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conductor no encontrado"));
            vehiculo.setConductor(conductor);
        } else {
            vehiculo.setConductor(null);
        }
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> obtenerPorId(Long idVehiculo) {
        return vehiculoRepository.findById(idVehiculo);
    }

    @Override
    public Vehiculo actualizar(Long idVehiculo, Vehiculo vehiculo) {
        Vehiculo existente = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado"));

        // Si cambia la placa, valida que no se repita en otro registro.
        if (!existente.getPlaca().equals(vehiculo.getPlaca()) && vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La placa ya esta registrada");
        }

        existente.setPlaca(vehiculo.getPlaca());
        existente.setMarca(vehiculo.getMarca());
        existente.setModelo(vehiculo.getModelo());
        existente.setAnio(vehiculo.getAnio());
        existente.setColor(vehiculo.getColor());

        // Si se envia conductor, valida que exista. Si no se envia, deja la relacion como esta.
        if (vehiculo.getConductor() != null && vehiculo.getConductor().getIdConductor() != null) {
            Conductor conductor = conductorRepository.findById(vehiculo.getConductor().getIdConductor())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conductor no encontrado"));
            existente.setConductor(conductor);
        }

        return vehiculoRepository.save(existente);
    }

    @Override
    public void eliminar(Long idVehiculo) {
        Vehiculo existente = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado"));
        vehiculoRepository.delete(existente);
    }
}
