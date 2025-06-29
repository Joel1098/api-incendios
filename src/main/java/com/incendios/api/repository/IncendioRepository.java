package com.incendios.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incendios.api.model.Incendio;

public interface IncendioRepository extends JpaRepository<Incendio, Long> {}
