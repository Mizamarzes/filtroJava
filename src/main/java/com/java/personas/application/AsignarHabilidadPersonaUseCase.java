package com.java.personas.application;

import java.sql.SQLException;

import com.java.personas.domain.PersonaService;
import com.java.personas.domain.Persons_skill;

public class AsignarHabilidadPersonaUseCase {
    private PersonaService personaService;

    public AsignarHabilidadPersonaUseCase(PersonaService personaService) {
        this.personaService = personaService;
    }

    public void asignarHabilidad(Persons_skill persons_skill) throws SQLException {
        personaService.asignarHabilidad(persons_skill);
    }
}
