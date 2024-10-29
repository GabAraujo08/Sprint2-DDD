package org.example.dao.veiculodao;

import org.example.entities.veiculo.Veiculo;
import org.example.exceptions.veiculo.VeiculoNotSavedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class VeiculoDaoImpl implements VeiculoDao{

    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    public Veiculo create(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotSavedException {
        final String sql = "INSERT INTO T_VEICULO (placa, fk_id_usuario, marca_veiculo, modelo_veiculo, ano_fabricacao, cor, km_veiculo, tipo_veiculo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setLong(2, veiculo.getProprietario().getId());
            pstmt.setString(3, veiculo.getMarca());
            pstmt.setString(4, veiculo.getModelo());
            pstmt.setString(5, String.valueOf(veiculo.getAno()));
            pstmt.setString(6, veiculo.getCor());
            pstmt.setInt(7, veiculo.getKilometragem());
            pstmt.setString(8, veiculo.getTipo());

            int linhasAlteradas = pstmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida
            if (linhasAlteradas == 0) {
                throw new VeiculoNotSavedException();
            }
        }catch (SQLException e){
            logger.warning("Erro ao salvar veiculo: "+e.getMessage());
        }
        return veiculo;
    }


//    @Override
//    public List<Usuario> readAll() throws SQLException {
//        return List.of();
//    }
//
//    @Override
//    public void delete(Long id) throws SQLException {
//
//    }
//
//    @Override
//    public void update(Veiculo veiculo) throws SQLException {
//
//    }
}
