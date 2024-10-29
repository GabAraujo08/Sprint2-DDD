package org.example.dao.veiculodao;

import org.example.config.DatabaseConnectionFactory;
import org.example.entities.usuario.Usuario;
import org.example.entities.veiculo.Veiculo;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.veiculo.VeiculoNotFoundException;
import org.example.exceptions.veiculo.VeiculoNotSavedException;
import org.example.service.usuario.UsuarioService;
import org.example.service.usuario.UsuarioServiceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VeiculoDaoImpl implements VeiculoDao{

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final UsuarioService usuarioService = UsuarioServiceFactory.create();

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


    @Override
    public List<Veiculo> readAll()  {
        final List<Veiculo> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_VEICULO";

        try(Connection conn = DatabaseConnectionFactory.create().get()){

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                // Obtém os valores obrigatórios para o construtor
                String placa = resultSet.getString("placa");
                Long proprietarioId = resultSet.getLong("fk_id_usuario"); // ID do usuário proprietário
                String marca = resultSet.getString("marca_veiculo");
                String modelo = resultSet.getString("modelo_veiculo");
                int anoFabricacao = resultSet.getInt("ano_fabricacao");
                String cor = resultSet.getString("cor");
                int kilometragem = resultSet.getInt("km_veiculo");
                String tipo = resultSet.getString("tipo_veiculo");
                //String chassi = resultSet.getString("chassi");

                System.out.println("Proprietário ID: " + proprietarioId);

                Usuario proprietario = usuarioService.findById(proprietarioId);
                Veiculo veiculo = new Veiculo(marca, modelo, anoFabricacao, placa, cor, kilometragem, proprietario, tipo);

                proprietario = null;


                System.out.println("Veículo encontrado: " + placa);
                all.add(veiculo);
            }

        } catch (SQLException e) {
            logger.warning("Não foi possível localizar nenhum registro de veículo: " + e.getMessage());
        } catch (UsuarioNotFoundException e) {
            throw new RuntimeException(e);
        }
        return all;
    }

    @Override
    public void delete(String placa, Connection connection) throws SQLException, VeiculoNotFoundException {
        final String sql = "DELETE FROM T_VEICULO WHERE placa = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, placa);
            int linhasAlteradas = pstmt.executeUpdate();
            if(linhasAlteradas == 0){
                throw new VeiculoNotFoundException();
            }
        }catch (SQLException e){
            logger.warning("Erro ao deletar veiculo: "+e.getMessage());
        }
    }

    @Override
    public Veiculo update(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotFoundException {
        return null;
    }
}
