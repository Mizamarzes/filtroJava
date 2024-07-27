package com.java.personas.domain;

import java.sql.SQLException;
import java.util.List;

public interface PersonaService {

    void createPerson(Persona persona) throws SQLException;

    void asignarHabilidad(Persons_skill persons_skill) throws SQLException;

    void eliminarPersona(int id) throws SQLException;

    List<Persona> consultarPersonasBySkill(int idskill) throws SQLException;

    void updateName(String new_name, int id) throws SQLException;
    void updateApellido(String new_apellido, int id) throws SQLException;
    void updateCity(int new_city, int id) throws SQLException;
    void updateAddress(String new_address, int id) throws SQLException;
    void updateAge(int new_age, int id) throws SQLException;
    void updateEmail(String new_email, int id) throws SQLException;
    void updateGender(int new_gender, int id) throws SQLException;



}
