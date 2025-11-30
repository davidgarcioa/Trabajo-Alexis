package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Entidad JPA que representa los vehiculos asegurados.
// Incluye relaciones a Conductor (1:1) y Seguro (1:N) segun el manual.
@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long idVehiculo;

    @Column(name = "placa", nullable = false, unique = true, length = 20)
    private String placa;

    @Column(name = "marca", nullable = false, length = 100)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "color", nullable = false, length = 50)
    private String color;

    // Relacion uno a uno: cada vehiculo tiene un conductor asignado y viceversa.
    @OneToOne
    @JoinColumn(name = "id_conductor")
    private Conductor conductor;

    // Relacion uno a muchos: un vehiculo puede tener varias polizas.
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seguro> seguros = new ArrayList<>();
}
