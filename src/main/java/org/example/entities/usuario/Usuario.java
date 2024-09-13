package org.example.entities.usuario;
import org.example.entities.validadores.Validadores;

import static org.example.entities.validadores.Validadores.removeCaracteresEspeciais;


public class Usuario{

    private String nome;
    private String endereco;
    private String email;
    private String senha;
    private String dataNascimento;
    private String cpf;
    private String telefone;

    public Usuario(String nome, String email, String senha, String cpf) {
        cpf = removeCaracteresEspeciais(cpf);
        if (nome.trim().isEmpty() || email.trim().isEmpty() || senha.trim().isEmpty() || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos");
        } else {

            this.nome = nome;

            this.email = email;
            if (senha.length() < 8) {
                throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
            }
            else{
                this.senha = senha;
            }
            if(!Validadores.isCPF(cpf)){
                throw new IllegalArgumentException("CPF inválido.");
            }
            this.cpf = cpf;
        }

    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
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


    public void setDataNascimento(String dataNascimento) {

        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        telefone = removeCaracteresEspeciais(telefone);
        if (telefone.length() < 11) {
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public void alterarSenha(String senha){
        if (senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
        }
        this.senha = senha;
    }

    public void alterarEmail(String email){
        this.email = email;
    }

    public void alterarEndereco(String endereco){
        this.endereco = endereco;
    }

    public void alterarNome(String nome){
        this.nome = nome;
    }

    public void excluirConta(){
        this.nome = null;
        this.endereco = null;
        this.email = null;
        this.senha = null;
        this.dataNascimento = null;
        this.cpf = null;
        this.telefone = null;
    }

    @Override
    public String toString() {
        return "Usuario {\n" +
                "  nome='" + nome + "',\n" +
                "  email='" + email + "',\n" +
                "  cpf='" + cpf + "',\n" +
                "  telefone='" + telefone + "',\n" +
                "  endereco='" + endereco + "',\n" +
                "  dataNascimento='" + dataNascimento + "'\n" +
                '}';
    }

}
