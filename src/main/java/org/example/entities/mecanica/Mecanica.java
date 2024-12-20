package org.example.entities.mecanica;

import java.util.ArrayList;
import java.util.List;

import org.example.entities.servico.Servico;

public class Mecanica {
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cnpjMecanica;

    // Construtor
    public Mecanica(Long id,String nome, String endereco, String telefone, String cnpjMecanica) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cnpjMecanica = cnpjMecanica;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpjMecanica() {
        return cnpjMecanica;
    }

    public void setCnpjMecanica(String cnpjMecanica) {
        this.cnpjMecanica = cnpjMecanica;
    }
}
