package com.java.personas.application;

import java.sql.SQLException;

import com.java.personas.domain.PersonaService;

public class ActualizarPersonaUseCase {
    private PersonaService personaService;

    public ActualizarPersonaUseCase(PersonaService personaService) {
        this.personaService = personaService;
    }

    public void updateName(String new_name, int id) throws SQLException {
        personaService.updateName(new_name, id);
    }
}
