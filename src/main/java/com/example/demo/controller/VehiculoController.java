package com.example.demo.controller;

import com.example.demo.entity.Vehiculo;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST que expone el CRUD de vehiculos.
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(vehiculoService.listar());
    }

    @GetMapping("/{idVehiculo}")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable Long idVehiculo) {
        return vehiculoService.obtenerPorId(idVehiculo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vehiculo> crear(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevo = vehiculoService.crear(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{idVehiculo}")
    public ResponseEntity<Vehiculo> actualizar(@PathVariable Long idVehiculo, @RequestBody Vehiculo vehiculo) {
        Vehiculo actualizado = vehiculoService.actualizar(idVehiculo, vehiculo);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idVehiculo}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idVehiculo) {
        vehiculoService.eliminar(idVehiculo);
        return ResponseEntity.noContent().build();
    }
}
