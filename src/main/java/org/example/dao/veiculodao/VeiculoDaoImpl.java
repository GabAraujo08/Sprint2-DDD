package org.example.dao.veiculodao;

import org.example.entities.usuario.Usuario;
import org.example.entities.veiculo.Veiculo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VeiculoDaoImpl implements VeiculoDao{

    private final Connection connection;

    public VeiculoDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO T_VEICULO(placa, fk_cpf, marca_veiculo, modelo_veiculo, ano_fabricacao, cor, km_veiculo, tipo) VALUES(?,?,?,?,?,?,?,?";

//        CREATE TABLE T_VEICULO(
//                placa varchar2(9) PRIMARY KEY,
//                fk_cpf CHAR(12),
//                FOREIGN KEY (fk_cpf) REFERENCES T_USUARIO(cpf),
//                marca_veiculo varchar2(20) NOT NULL,
//                modelo_veiculo varchar2(50) NOT NULL,
//                ano_fabricacao CHAR(4),
//                cor varchar2(30),
//                km_veiculo INT CHECK (km_veiculo >= 0),
//                tipo varchar2(20)
//        );

    }

    @Override
    public List<Usuario> readAll() throws SQLException {
        return List.of();
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Veiculo veiculo) throws SQLException {

    }
}
