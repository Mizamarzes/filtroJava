package com.java.personas.infrastructure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.databaseconfig.DatabaseConfig;
import com.java.personas.domain.Persona;
import com.java.personas.domain.PersonaService;
import com.java.personas.domain.Persons_skill;

public class PersonaRepository implements PersonaService {

    private Connection connection;

    public PersonaRepository() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (Exception e) {
            System.out.println("Error en la conexion");
        }
    }

    // CREATE PERSON

    @Override
    public void createPerson(Persona persona) throws SQLException{
        String query = "{CALL createPerson(?, ?, ?, ?, ?, ?, ?)}";
        try(CallableStatement cs = connection.prepareCall(query)){
            cs.setString(1, persona.getName());
            cs.setString(2, persona.getLastname());
            cs.setInt(3, persona.getIdcity());
            cs.setString(4, persona.getAddress());
            cs.setInt(5, persona.getAge());
            cs.setString(6, persona.getEmail());
            cs.setInt(7, persona.getIdgender());
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // ASIGNAR HABILIDAD PERSONA

    @Override
    public void asignarHabilidad(Persons_skill persons_skill) throws SQLException{
        String query = "{CALL asignarHabilidadPersona(?, ?, ?)}";
        try(CallableStatement cs = connection.prepareCall(query)){
            cs.setDate(1, persons_skill.getRegistration_date());
            cs.setInt(2, persons_skill.getIdperson());
            cs.setInt(3, persons_skill.getIdskill());
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // ELIMINAR PERSONA

    @Override
    public void eliminarPersona(int id) throws SQLException {
        String query = "DELETE FROM persons WHERE id = ?";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setInt(1, id);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // CONSULTAR PERSONAS

    @Override
    public List<Persona> consultarPersonasBySkill(int idskill) throws SQLException{
        String query = "SELECT p.name FROM persons AS p JOIN persons_skill AS ps ON ps.iperson = p.id WHERE ps.idskill = ?";
        List<Persona> personas = new ArrayList<>();

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setInt(1, idskill);
        
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Persona persona = new Persona();
                    persona.setName(rs.getString("name"));
                    personas.add(persona);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return personas;
    }

    // ACTUALIZAR DATOS PERSONA

    @Override
    public void updateName(String new_name, int id) throws SQLException{
        String query = "UPDATE persons SET name = ? WHERE id = ?";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, new_name);
            cs.setInt(2, id);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateApellido(String new_apellido, int id) throws SQLException{
        String query = "UPDATE persons SET lastname = ? WHERE id = ?";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, new_apellido);
            cs.setInt(2, id);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateCity(int new_city, int id) throws SQLException{
        String query = "UPDATE persons SET idcity = ? WHERE id = ?";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setInt(1, new_city);
            cs.setInt(2, id);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateAddress(String new_address, int id) throws SQLException{
        String query = "UPDATE persons SET address = ? WHERE id = ?";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, new_address);
            cs.setInt(2, id);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateAge(int new_age, int id) throws SQLException{
        String query = "UPDATE persons SET age = ? WHERE id = ?";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setInt(1, new_age);
            cs.setInt(2, id);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateEmail(String new_email, int id) throws SQLException{
        String query = "UPDATE persons SET email = ? WHERE id = ?";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, new_email);
            cs.setInt(2, id);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateGender(int new_gender, int id) throws SQLException{
        String query = "UPDATE persons SET idgender = ? WHERE id = ?";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setInt(1, new_gender);
            cs.setInt(2, id);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
