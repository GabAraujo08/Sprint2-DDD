package org.example.dtos;

import java.util.ArrayList;
import java.util.List;

import org.example.entities.servico.Servico;

public class MecanicaDto {
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cnpjMecanica;


    public MecanicaDto() {


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
