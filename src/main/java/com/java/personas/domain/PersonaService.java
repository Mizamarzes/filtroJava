package com.java.personas.domain;

import java.sql.SQLException;
import java.util.List;

public interface PersonaService {

    void createPerson(Persona persona) throws SQLException;

    void asignarHabilidad(Persons_skill persons_skill) throws SQLException;

    void eliminarPersona(int id) throws SQLException;

    List<Persona> consultarPersonasBySkill(int idskill) throws SQLException;

    void updateName(String new_name, int id) throws SQLException;

}
