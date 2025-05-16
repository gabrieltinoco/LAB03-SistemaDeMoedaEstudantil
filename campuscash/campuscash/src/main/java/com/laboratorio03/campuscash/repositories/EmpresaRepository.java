package com.laboratorio03.campuscash.repositories;

import com.laboratorio03.campuscash.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}