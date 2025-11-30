package com.example.demo.controller;

import com.example.demo.entity.Conductor;
import com.example.demo.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// API REST para CRUD de conductores.
@RestController
@RequestMapping("/api/conductores")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    @GetMapping
    public ResponseEntity<List<Conductor>> listar() {
        return ResponseEntity.ok(conductorService.listar());
    }

    @GetMapping("/{idConductor}")
    public ResponseEntity<Conductor> obtenerPorId(@PathVariable Long idConductor) {
        return conductorService.obtenerPorId(idConductor)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Conductor> crear(@RequestBody Conductor conductor) {
        Conductor nuevo = conductorService.crear(conductor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{idConductor}")
    public ResponseEntity<Conductor> actualizar(@PathVariable Long idConductor, @RequestBody Conductor conductor) {
        Conductor actualizado = conductorService.actualizar(idConductor, conductor);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idConductor}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idConductor) {
        conductorService.eliminar(idConductor);
        return ResponseEntity.noContent().build();
    }
}
