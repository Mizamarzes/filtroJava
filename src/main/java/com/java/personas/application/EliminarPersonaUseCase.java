package com.java.personas.application;

import java.sql.SQLException;

import com.java.personas.domain.PersonaService;

public class EliminarPersonaUseCase {
    private PersonaService personaService;

    public EliminarPersonaUseCase(PersonaService personaService) {
        this.personaService = personaService;
    }

    public void eliminarPersona(int id) throws SQLException{
        personaService.eliminarPersona(id);
    }
}
