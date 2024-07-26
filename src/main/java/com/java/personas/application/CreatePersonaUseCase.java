package com.java.personas.application;

import java.sql.SQLException;

import com.java.personas.domain.Persona;
import com.java.personas.domain.PersonaService;

public class CreatePersonaUseCase {

    private PersonaService personaService;

    public CreatePersonaUseCase(PersonaService personaService) {
        this.personaService = personaService;
    }

    public void createPerson(Persona persona) throws SQLException{
        personaService.createPerson(persona);
    }
}
