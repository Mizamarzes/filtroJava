package com.java;

import java.sql.SQLException;

import com.java.databaseconfig.DatabaseConfig;
import com.java.helpers.ConsoleUtils;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            DatabaseConfig.getConnection();
            // System.out.println("Conexion exitosa");
            mainMenu();
        } catch (Exception e) {
            System.out.println("No se pudo conectar");
        }
        
    }

    public static void mainMenu() {
        do {
            ConsoleUtils.clear();
            System.out.println("SISTEMA DE GESTION DE USUARIOS ");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Asignar habilidad");
            System.out.println("3. Creacion de nuevas habilidades");
            System.out.println("4. Consulta de personas basadas en habilidad especifica");
            System.out.println("5. Actualizacion de informacion de personas existentes");
            System.out.println("6. Eliminacion de registros de personas");
            System.out.println("7. Salir");
            System.out.println("");
            System.out.println("Selecciona: ");
            int op = ConsoleUtils.verifyEntryInt(1, 7);

            switch (op) {
                case 1:
                    System.out.println("monda");
                    ConsoleUtils.waitWindow();
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    return;
                default:
                    break;
        }
        } while (true);
        
    }
}