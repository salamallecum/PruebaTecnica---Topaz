package com.pruebaTecnicaTopaz.persistence.repository;

import com.pruebaTecnicaTopaz.persistence.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}

