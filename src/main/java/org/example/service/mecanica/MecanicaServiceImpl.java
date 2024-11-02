package org.example.service.mecanica;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.mecanicadao.MecanicaDao;
import org.example.dao.mecanicadao.MecanicaDaoFactory;
import org.example.entities.mecanica.Mecanica;
import org.example.exceptions.mecanica.MecanicaNotFoundException;
import org.example.exceptions.mecanica.MecanicaNotSavedException;
import org.example.exceptions.servico.ServicoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MecanicaServiceImpl implements MecanicaService {
    private final MecanicaDao dao = MecanicaDaoFactory.create();

    @Override
    public Mecanica create(Mecanica mecanica) throws MecanicaNotSavedException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try{
            mecanica = this.dao.create(mecanica, connection);
            connection.commit();
            return mecanica;
        }catch (SQLException | ServicoNotSavedException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Mecanica> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Mecanica update(Mecanica mecanica) throws SQLException, MecanicaNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            this.dao.update(mecanica, connection);
            connection.commit();
            return mecanica;
        } catch (SQLException | MecanicaNotFoundException e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws SQLException, MecanicaNotFoundException {

    }

    @Override
    public Mecanica findById(Long id) throws SQLException, MecanicaNotFoundException {
        return null;
    }
}
