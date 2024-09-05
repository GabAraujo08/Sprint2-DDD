package org.example.usuario;

import org.example.validadores.Validadores;

import javax.xml.validation.Validator;
import java.util.Date;
import java.util.InputMismatchException;



public class Usuario {
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





}
