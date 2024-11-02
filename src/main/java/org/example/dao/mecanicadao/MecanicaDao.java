

package org.example.dao.mecanicadao;



import org.example.entities.mecanica.Mecanica;
import org.example.exceptions.mecanica.MecanicaNotFoundException;
import org.example.exceptions.mecanica.MecanicaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MecanicaDao {

    Mecanica create(Mecanica mecanica, Connection connection) throws SQLException, MecanicaNotSavedException;

    List<Mecanica> readAll() ;

    Mecanica update(Mecanica mecanica, Connection connection) throws SQLException, MecanicaNotFoundException;

    void delete(Long id, Connection connection) throws MecanicaNotFoundException, SQLException;

    Mecanica findById(Long id, Connection connection) throws MecanicaNotFoundException, SQLException;
}