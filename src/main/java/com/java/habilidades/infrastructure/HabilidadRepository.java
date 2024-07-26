package com.java.habilidades.infrastructure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.java.databaseconfig.DatabaseConfig;
import com.java.habilidades.domain.HabilidadService;

public class HabilidadRepository implements HabilidadService {
    
    private Connection connection;

    public HabilidadRepository() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (Exception e) {
            System.out.println("Error en la conexion");
        }
    }

    // CREAR HABILIDAD

    @Override
    public void crearHabilidad(String name) throws SQLException{
        String query = "INSERT INTO skill(id, name) VALUES (NULL, ?)";
        try(CallableStatement cs = connection.prepareCall(query)){
            cs.setString(1, name);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
