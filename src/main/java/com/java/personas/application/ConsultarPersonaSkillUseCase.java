package com.java.personas.application;

import java.sql.SQLException;
import java.util.List;

import com.java.personas.domain.Persona;
import com.java.personas.domain.PersonaService;

public class ConsultarPersonaSkillUseCase {
    private PersonaService personaService;

    public ConsultarPersonaSkillUseCase(PersonaService personaService) {
        this.personaService = personaService;
    }

    public List<Persona> consultarPersonasBySkill(int idskill) throws SQLException{
        List<Persona> personas = personaService.consultarPersonasBySkill(idskill);
        return personas;
    }
}
