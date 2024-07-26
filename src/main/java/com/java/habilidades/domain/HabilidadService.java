package com.java.habilidades.domain;

import java.sql.SQLException;

public interface HabilidadService {

    void crearHabilidad(String name) throws SQLException;
}
