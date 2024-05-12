package org.example.usuario;

import java.util.Date;

public class Usuario {
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String dataNascimento;
    private String cpf;
    private String telefone;

    public Usuario(String nome, String sobrenome, String email, String senha, String dataNascimento, String cpf, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
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
        // TODO: É necessário que exista uma validação para que se possa visualizar a senha
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




}
