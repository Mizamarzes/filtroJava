package com.java;

import java.sql.SQLException;

import com.java.databaseconfig.DatabaseConfig;
import com.java.habilidades.application.CreateHabilidadUseCase;
import com.java.habilidades.domain.HabilidadService;
import com.java.habilidades.infrastructure.HabilidadController;
import com.java.habilidades.infrastructure.HabilidadRepository;
import com.java.helpers.ConsoleUtils;
import com.java.helpers.Validators;
import com.java.personas.application.ActualizarPersonaUseCase;
import com.java.personas.application.AsignarHabilidadPersonaUseCase;
import com.java.personas.application.ConsultarPersonaSkillUseCase;
import com.java.personas.application.CreatePersonaUseCase;
import com.java.personas.application.EliminarPersonaUseCase;
import com.java.personas.domain.PersonaService;
import com.java.personas.infrastructure.PersonaController;
import com.java.personas.infrastructure.PersonaRepository;

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

    public static void mainMenu() throws SQLException {

        PersonaService ps = new PersonaRepository();
        HabilidadService hs = new HabilidadRepository();

        CreatePersonaUseCase cpuc = new CreatePersonaUseCase(ps);
        AsignarHabilidadPersonaUseCase ahpuc = new AsignarHabilidadPersonaUseCase(ps);
        EliminarPersonaUseCase epuc = new EliminarPersonaUseCase(ps);
        ConsultarPersonaSkillUseCase cpsuc = new ConsultarPersonaSkillUseCase(ps);
        ActualizarPersonaUseCase apuc = new ActualizarPersonaUseCase(ps);

        CreateHabilidadUseCase chuc = new CreateHabilidadUseCase(hs);

        Validators validators = new Validators();

        PersonaController pc = new PersonaController(cpuc, ahpuc, epuc, cpsuc, validators, apuc);
        HabilidadController hc = new HabilidadController(chuc);

        do {
            ConsoleUtils.clear();
            System.out.println("SISTEMA DE GESTION DE PERSONAS ");
            System.out.println("1. Registrar persona");
            System.out.println("2. Asignar habilidad");
            System.out.println("3. Creacion de nuevas habilidades");
            System.out.println("4. Consulta de personas basadas en habilidad especifica");
            System.out.println("5. Actualizacion de informacion de personas existentes");
            System.out.println("6. Eliminar persona");
            System.out.println("7. Salir");
            System.out.println("");
            System.out.println("Selecciona: ");
            int op = ConsoleUtils.verifyEntryInt(1, 7);

            switch (op) {
                case 1:
                    pc.createPersonaController();
                    break;
                case 2:
                    pc.asignarHabilidadPersonaController();
                    break;
                case 3:
                    hc.createHabilidadController();
                    break;
                case 4:
                    pc.consultarPersonaSkillController();
                    break;
                case 5:
                    pc.updatePersonaController();
                    break;
                case 6:
                    pc.eliminarPersonaController();
                    break;
                case 7:
                    System.out.println("La buena profe");
                    return;
                default:
                    break;
        }
        } while (true);
        
    }
}