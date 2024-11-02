

package org.example.dao.servicodao;



import org.example.entities.servico.Servico;
import org.example.exceptions.servico.ServicoNotFoundException;
import org.example.exceptions.servico.ServicoNotSavedException;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ServicoDao {

    Servico create(Servico servico, Connection connection) throws SQLException, ServicoNotSavedException;

    List<Servico> readAll() ;

    Servico update(Servico usuario, Connection connection) throws SQLException, ServicoNotFoundException;

    void delete(Long id, Connection connection) throws ServicoNotFoundException, SQLException;

    Servico findById(Long id, Connection connection) throws ServicoNotFoundException, SQLException;
}