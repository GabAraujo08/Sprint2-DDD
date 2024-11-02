package org.example.service.mecanica;


import org.example.entities.mecanica.Mecanica;
import org.example.exceptions.mecanica.MecanicaNotFoundException;
import org.example.exceptions.mecanica.MecanicaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MecanicaService {
    Mecanica create(Mecanica mecanica) throws MecanicaNotSavedException, SQLException;

    List<Mecanica> readAll() ;

    Mecanica update(Mecanica mecanica) throws SQLException, MecanicaNotFoundException;

    void delete(Long id) throws SQLException, MecanicaNotFoundException;

    Mecanica findById(Long id) throws SQLException, MecanicaNotFoundException;
}
