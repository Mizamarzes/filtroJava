package com.java.habilidades.application;

import java.sql.SQLException;

import com.java.habilidades.domain.HabilidadService;

public class CreateHabilidadUseCase {
    private HabilidadService habilidadService;

    public CreateHabilidadUseCase(HabilidadService habilidadService) {
        this.habilidadService = habilidadService;
    }

    public void crearHabilidad(String name) throws SQLException {
        habilidadService.crearHabilidad(name);
    }
}
