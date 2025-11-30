package com.example.demo.repository;

import com.example.demo.entity.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Acceso a datos para Seguros (polizas).
@Repository
public interface SeguroRepository extends JpaRepository<Seguro, String> {

    List<Seguro> findByVehiculoIdVehiculo(Long idVehiculo);
}
