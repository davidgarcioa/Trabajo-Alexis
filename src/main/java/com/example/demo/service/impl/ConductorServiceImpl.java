package com.example.demo.service.impl;

import com.example.demo.entity.Conductor;
import com.example.demo.repository.ConductorRepository;
import com.example.demo.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// Implementa la logica CRUD para conductores.
@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public Conductor crear(Conductor conductor) {
        return conductorRepository.save(conductor);
    }

    @Override
    public List<Conductor> listar() {
        return conductorRepository.findAll();
    }

    @Override
    public Optional<Conductor> obtenerPorId(Long idConductor) {
        return conductorRepository.findById(idConductor);
    }

    @Override
    public Conductor actualizar(Long idConductor, Conductor conductor) {
        Conductor existente = conductorRepository.findById(idConductor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conductor no encontrado"));

        existente.setNombre(conductor.getNombre());
        existente.setLicencia(conductor.getLicencia());
        existente.setTelefono(conductor.getTelefono());
        existente.setDireccion(conductor.getDireccion());
        existente.setActivo(conductor.getActivo());

        return conductorRepository.save(existente);
    }

    @Override
    public void eliminar(Long idConductor) {
        Conductor existente = conductorRepository.findById(idConductor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conductor no encontrado"));
        conductorRepository.delete(existente);
    }
}
