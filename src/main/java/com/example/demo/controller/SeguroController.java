package com.example.demo.controller;

import com.example.demo.entity.Seguro;
import com.example.demo.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// API REST para CRUD de seguros y consulta por vehiculo.
@RestController
@RequestMapping("/api/seguros")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;

    @GetMapping
    public ResponseEntity<List<Seguro>> listar() {
        return ResponseEntity.ok(seguroService.listar());
    }

    @GetMapping("/{idSeguro}")
    public ResponseEntity<Seguro> obtenerPorId(@PathVariable String idSeguro) {
        return seguroService.obtenerPorId(idSeguro)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Seguro> crear(@RequestBody Seguro seguro) {
        Seguro nuevo = seguroService.crear(seguro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{idSeguro}")
    public ResponseEntity<Seguro> actualizar(@PathVariable String idSeguro, @RequestBody Seguro seguro) {
        Seguro actualizado = seguroService.actualizar(idSeguro, seguro);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idSeguro}")
    public ResponseEntity<Void> eliminar(@PathVariable String idSeguro) {
        seguroService.eliminar(idSeguro);
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional: listar todos los seguros de un vehiculo.
    @GetMapping("/vehiculo/{idVehiculo}")
    public ResponseEntity<List<Seguro>> listarPorVehiculo(@PathVariable Long idVehiculo) {
        return ResponseEntity.ok(seguroService.obtenerPorVehiculo(idVehiculo));
    }
}
