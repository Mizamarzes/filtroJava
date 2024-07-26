package com.java.habilidades.infrastructure;

import java.sql.SQLException;

import com.java.habilidades.application.CreateHabilidadUseCase;
import com.java.helpers.ConsoleUtils;

public class HabilidadController {
    private final CreateHabilidadUseCase createHabilidadUseCase;

    public HabilidadController(CreateHabilidadUseCase createHabilidadUseCase) {
        this.createHabilidadUseCase = createHabilidadUseCase;
    }

    public void createHabilidadController() throws SQLException{
        ConsoleUtils.clear();

        System.out.println("Inserte el nombre de la habilidad: ");
        String habilidad = ConsoleUtils.verifyEntryString();

        try {
            createHabilidadUseCase.crearHabilidad(habilidad);;
            System.out.println("Habilidad creada con exito");
        } catch (Exception e) {
            System.out.println("Error al crear habilidad");
        }
        ConsoleUtils.waitWindow();
    }
}
