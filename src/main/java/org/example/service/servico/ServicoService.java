package org.example.service.servico;

import org.example.entities.servico.Servico;
import org.example.exceptions.servico.ServicoNotFoundException;
import org.example.exceptions.servico.ServicoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ServicoService {
    Servico create(Servico servico) throws ServicoNotSavedException, SQLException;

    List<Servico> readAll() ;

    Servico update(Servico servico) throws SQLException, ServicoNotFoundException;

    void delete(Long id) throws SQLException, ServicoNotFoundException;

    Servico findById(Long id) throws SQLException, ServicoNotFoundException;
}
