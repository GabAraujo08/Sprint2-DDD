package org.example.usuario;
import org.example.config.DatabaseConfig;
import org.example.validadores.Validadores;
import javax.xml.validation.Validator;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;


public class Usuario implements UsuarioDao {

    private final Connection connection;

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String dataNascimento;
    private String cpf;
    private String telefone;

    public Usuario(String nome, String sobrenome, String email, String senha) {
        if (nome.trim().isEmpty() || sobrenome.trim().isEmpty() || email.trim().isEmpty() || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos");
        }else{

            this.nome = nome;
            this.sobrenome = sobrenome;
            this.email = email;
            if (senha.length() < 8) {
                throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
            }
            else{
                this.senha = senha;
            }
        }

    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        // TODO: É necessário que exista uma validação para que se possa visualizar a senha ao invés do Hash
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setCpf(String cpf) {
       if(!Validadores.isCPF(cpf)){
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    public void setDataNascimento(String dataNascimento) {

        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        telefone = Validadores.removeCaracteresEspeciais(telefone);
        if (telefone.length() < 11) {
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public void alterarSenha(String senha){
        this.senha = senha;
    }

    public void alterarTelefone(String telefone){
        this.telefone = telefone;
    }

    public void alterarEmail(String email){
        this.email = email;
    }

    public void alterarSobrenome(String sobrenome){
        this.sobrenome = sobrenome;
    }

    public void alterarNome(String nome){
        this.nome = nome;
    }

    public void excluirConta(){
        this.nome = null;
        this.sobrenome = null;
        this.email = null;
        this.senha = null;
        this.dataNascimento = null;
        this.cpf = null;
        this.telefone = null;
    }


    @Override
    public void create(Usuario usuario) throws SQLException {
        String sql = "insert into t_usuario(nome, sobrenome, email, senha, data_nascimento, cpf, telefone) values(?,?,?,?,?,?,?)";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, usuario.getNome());
        pstmt.setString(2, usuario.getSobrenome());
        pstmt.setString(3, usuario.getEmail());
        pstmt.setString(4, usuario.getSenha());
        pstmt.setString(5, usuario.getDataNascimento());
        pstmt.setString(6, usuario.getCpf());
        pstmt.setString(7, usuario.getTelefone());
    }

    @Override
    public List<Usuario> readAll() throws SQLException {
        String sql = "SELECT * FROM t_usuario";
        List<Usuario> result = new ArrayList<>();
        Statement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            int idade = rs.getInt("idade");
            result.add(new Usuario(nome, sobrenome, email, senha));
        }
        return result;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
