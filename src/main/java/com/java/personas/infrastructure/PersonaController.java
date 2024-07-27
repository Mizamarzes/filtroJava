package com.java.personas.infrastructure;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.java.helpers.ConsoleUtils;
import com.java.helpers.Validators;
import com.java.personas.application.ActualizarPersonaUseCase;
import com.java.personas.application.AsignarHabilidadPersonaUseCase;
import com.java.personas.application.ConsultarPersonaSkillUseCase;
import com.java.personas.application.CreatePersonaUseCase;
import com.java.personas.application.EliminarPersonaUseCase;
import com.java.personas.domain.Persona;
import com.java.personas.domain.Persons_skill;

public class PersonaController {
    private final CreatePersonaUseCase createPersonaUseCase;
    private final AsignarHabilidadPersonaUseCase asignarHabilidadPersonaUseCase;
    private final EliminarPersonaUseCase eliminarPersonaUseCase;
    private final ConsultarPersonaSkillUseCase consultarPersonaSkillUseCase;

    private final Validators validators;

    private final ActualizarPersonaUseCase actualizarPersonaUseCase;

    public PersonaController(CreatePersonaUseCase createPersonaUseCase,
        AsignarHabilidadPersonaUseCase asignarHabilidadPersonaUseCase,
        EliminarPersonaUseCase eliminarPersonaUseCase,
        ConsultarPersonaSkillUseCase consultarPersonaSkillUseCase,
        Validators validators,
        ActualizarPersonaUseCase actualizarPersonaUseCase) {
        this.createPersonaUseCase = createPersonaUseCase;
        this.asignarHabilidadPersonaUseCase = asignarHabilidadPersonaUseCase;
        this.eliminarPersonaUseCase = eliminarPersonaUseCase;
        this.consultarPersonaSkillUseCase = consultarPersonaSkillUseCase;
        this.validators = validators;
        this.actualizarPersonaUseCase = actualizarPersonaUseCase;
    }

    public void createPersonaController() throws SQLException{
        ConsoleUtils.clear();

        System.out.println("Inserte nombre del usuario");
        String name = ConsoleUtils.verifyEntryString();

        System.out.println("Inserte apellido: ");
        String lastname = ConsoleUtils.verifyEntryString();

        System.out.println("Inserte id de la ciudad: ");
        int idcity = ConsoleUtils.verifyingIntNoRange();

        if (!validators.checkIdExistsINT("city", "id", idcity)) {
            return; 
        }

        System.out.println("Inserte direccion: ");
        String address = ConsoleUtils.verifyEntryString();

        System.out.println("Inserte edad: ");
        int age = ConsoleUtils.verifyingIntNoRange();

        System.out.println("Inserte email: ");
        String email = ConsoleUtils.verifyEntryString();

        System.out.println("Inserte id genero: ");
        int idgender = ConsoleUtils.verifyingIntNoRange();

        if (!validators.checkIdExistsINT("gender", "id", idgender)) {
            return; 
        }

        Persona persona = new Persona(name, lastname, idcity, address, age, email, idgender);

        try {
            createPersonaUseCase.createPerson(persona);
            System.out.println("Persona creada con exito");
        } catch (Exception e) {
            System.out.println("Error al crear persona");
        }
        ConsoleUtils.waitWindow();
    }

    public void asignarHabilidadPersonaController() throws SQLException{
        ConsoleUtils.clear();

        System.out.println("Inserte fecha de registro (yyyy-mm-ddd)");
        Date date = ConsoleUtils.verifyDate();

        System.out.println("Inserte id de person: ");
        int idperson = ConsoleUtils.verifyingIntNoRange();

        if (!validators.checkIdExistsINT("persons", "id", idperson)) {
            return; 
        }

        System.out.println("Inserte id de la skill: ");
        int idskill = ConsoleUtils.verifyingIntNoRange();

        if (!validators.checkIdExistsINT("skill", "id", idskill)) {
            return; 
        }

        Persons_skill persons_skill = new Persons_skill(date, idperson, idskill);

        try {
            asignarHabilidadPersonaUseCase.asignarHabilidad(persons_skill);;
            System.out.println("Habilidad asignada con exito");
        } catch (Exception e) {
            System.out.println("Error al asignar habilidad");
        }
        ConsoleUtils.waitWindow();
    }

    public void eliminarPersonaController() throws SQLException{
        ConsoleUtils.clear();

        System.out.println("Inserte la id de la persona a eliminar: ");
        int id = ConsoleUtils.verifyingIntNoRange();

        if (!validators.checkIdExistsINT("persons", "id", id)) {
            return; 
        }

        try {
            eliminarPersonaUseCase.eliminarPersona(id);
            System.out.println("Persona eliminada con exito");
        } catch (Exception e) {
            System.out.println("Error al eliminar Persona");
        }
        ConsoleUtils.waitWindow();

    }

    public void consultarPersonaSkillController() throws SQLException{
        ConsoleUtils.clear();

        System.out.println("Inserte el id de la skill para consultar las personas");
        int idskill = ConsoleUtils.verifyingIntNoRange();

        if (!validators.checkIdExistsINT("skill", "id", idskill)) {
            return; 
        }

        try {
            ConsoleUtils.clear();
            List<Persona> personas = consultarPersonaSkillUseCase.consultarPersonasBySkill(idskill);
            System.out.println("PERSONAS CON LA HABILIDAD DE ID: " + idskill);
            for(Persona persona: personas) {
                System.out.println(persona.getName());
            }
            
            System.out.println("");
            System.out.println("Consulta ejecutada con exito");
        } catch (Exception e) {
            System.out.println("Error al consultar");
        }
        ConsoleUtils.waitWindow();

    }


    public void updatePersonaController() throws SQLException{
        ConsoleUtils.clear();

        System.out.println("Ingrese el id de la persona a modificar");
        int idperson = ConsoleUtils.verifyingIntNoRange();

        if (!validators.checkIdExistsINT("persons", "id", idperson)) {
            return; 
        }

        ConsoleUtils.clear();
        System.out.println("--- MENU DE MODIFICAR PERSONA --- ");
        System.out.println("Que desea modificar?");
        System.out.println("");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. ciudad");
        System.out.println("4. direccion");
        System.out.println("5. edad");
        System.out.println("6. email");
        System.out.println("7. genero");
        System.out.println("8. Salir");

        int op = ConsoleUtils.verifyEntryInt(1, 5);

        switch (op) {
            case 1:
                ConsoleUtils.clear();
                System.out.println("Ingrese el nuevo nombre: ");
                String new_name = ConsoleUtils.verifyEntryString();
                try {
                    actualizarPersonaUseCase.updateName(new_name, idperson);
                    System.out.println("");
                    System.out.println("Persona actualizada con exito");
                } catch (Exception e) {
                    System.out.println("Error al actualizar");
                }
                break;
            case 2:
                ConsoleUtils.clear();
                System.out.println("Ingrese el nuevo apellido: ");
                String new_apellido = ConsoleUtils.verifyEntryString();
                try {
                    actualizarPersonaUseCase.updateApellido(new_apellido, idperson);
                    System.out.println("");
                    System.out.println("Persona actualizada con exito");
                } catch (Exception e) {
                    System.out.println("Error al actualizar");
                }
                break;
            case 3:
                ConsoleUtils.clear();
                System.out.println("Ingrese la id de la nueva ciudad: ");
                int new_city = ConsoleUtils.verifyingIntNoRange();

                if (!validators.checkIdExistsINT("city", "id", new_city)) {
                    return; 
                }
        
                try {
                    actualizarPersonaUseCase.updateCity(new_city, idperson);
                    System.out.println("");
                    System.out.println("Persona actualizada con exito");
                } catch (Exception e) {
                    System.out.println("Error al actualizar");
                }
                break;
            case 4:
                
                break;
            case 5:
                System.out.println("En proceso");
                break;
            case 6:
                System.out.println("En proceso");
                break;
            case 7:
                System.out.println("En proceso");
                break;
            case 8:
                
                return;
            default:
                break;
        }

        ConsoleUtils.waitWindow();

    }

}
