package com.example.demo.repository;

import com.example.demo.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Acceso a datos para Conductor.
@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}
