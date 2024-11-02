package org.example.service.servico;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.servicodao.ServicoDao;
import org.example.dao.servicodao.ServicoDaoFactory;
import org.example.entities.servico.Servico;
import org.example.exceptions.servico.ServicoNotFoundException;
import org.example.exceptions.servico.ServicoNotSavedException;
import org.example.exceptions.usuario.UsuarioNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServicoServiceImpl implements ServicoService {

    private final ServicoDao dao = ServicoDaoFactory.create();

    @Override
    public Servico create(Servico servico) throws ServicoNotSavedException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try{
            servico = this.dao.create(servico, connection);
            connection.commit();
            return servico;
        }catch (SQLException | ServicoNotSavedException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Servico> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Servico update(Servico servico) throws SQLException, ServicoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            this.dao.update(servico, connection);
            connection.commit();
            return servico;
        } catch (SQLException | ServicoNotFoundException e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws SQLException, ServicoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            this.dao.delete(id, connection);
            connection.commit();
        } catch (SQLException | ServicoNotFoundException e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public Servico findById(Long id) throws SQLException, ServicoNotFoundException {
        return null;
    }
}
