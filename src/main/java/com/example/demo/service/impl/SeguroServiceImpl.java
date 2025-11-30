package com.example.demo.service.impl;

import com.example.demo.entity.Seguro;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repository.SeguroRepository;
import com.example.demo.repository.VehiculoRepository;
import com.example.demo.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// Implementa la logica CRUD para polizas y consulta por vehiculo.
@Service
public class SeguroServiceImpl implements SeguroService {

    @Autowired
    private SeguroRepository seguroRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public Seguro crear(Seguro seguro) {
        // Valida que el vehiculo exista antes de asociar la poliza.
        Vehiculo vehiculo = vehiculoRepository.findById(seguro.getVehiculo().getIdVehiculo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehiculo no encontrado"));
        seguro.setVehiculo(vehiculo);
        return seguroRepository.save(seguro);
    }

    @Override
    public List<Seguro> listar() {
        return seguroRepository.findAll();
    }

    @Override
    public Optional<Seguro> obtenerPorId(String idSeguro) {
        return seguroRepository.findById(idSeguro);
    }

    @Override
    public Seguro actualizar(String idSeguro, Seguro seguro) {
        Seguro existente = seguroRepository.findById(idSeguro)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguro no encontrado"));

        Vehiculo vehiculo = vehiculoRepository.findById(seguro.getVehiculo().getIdVehiculo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehiculo no encontrado"));

        existente.setVehiculo(vehiculo);
        existente.setCompania(seguro.getCompania());
        existente.setNumeroPoliza(seguro.getNumeroPoliza());
        existente.setFechaInicio(seguro.getFechaInicio());
        existente.setFechaVencimiento(seguro.getFechaVencimiento());

        return seguroRepository.save(existente);
    }

    @Override
    public void eliminar(String idSeguro) {
        Seguro existente = seguroRepository.findById(idSeguro)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguro no encontrado"));
        seguroRepository.delete(existente);
    }

    @Override
    public List<Seguro> obtenerPorVehiculo(Long idVehiculo) {
        // Devuelve todas las polizas asociadas a un vehiculo especifico.
        return seguroRepository.findByVehiculoIdVehiculo(idVehiculo);
    }
}
