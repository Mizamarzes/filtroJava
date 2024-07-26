package com.java.helpers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.databaseconfig.DatabaseConfig;

public class Validators {

    private Connection connection;

    // Bloque estático para inicializar la conexión
    public Validators() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIdExistsINT(String tableName, String columnName, int idValue) throws SQLException {
        boolean exists = false;
        String query = "{CALL CheckIdExistsINT(?, ?, ?)}";

        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, tableName);
            cs.setString(2, columnName);
            cs.setInt(3, idValue);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt("id_exists") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-lanzar la excepción para el manejo externo
        }

        if (!exists) {
            System.out.println("The " + columnName + " with value " + idValue + " does not exist in the " + tableName + " table.");
            ConsoleUtils.waitWindow();
        }

        return exists;
    }
}


