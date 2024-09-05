package org.example.veiculo;

import org.example.usuario.Usuario;

public class Veiculo {
    private String marca;
    private String modelo;
    private Integer ano;
    private String placa;
    private String cor;
    private Integer kilometragem;
    private Usuario proprietario;
    private String chassi;


    public Veiculo(String marca, String modelo, Integer ano, String placa, String cor, Integer kilometragem, Usuario proprietario, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.cor = cor;
        this.kilometragem = kilometragem;
        this.proprietario = proprietario;
        this.chassi = chassi;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getAno() {
        return ano;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public Integer getKilometragem() {
        return kilometragem;
    }

    public String getCor() {
        return cor;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public String getChassi() {
        return chassi;
    }

    public void atualizarKilometragem(Integer kilometragem){
        try{
            if (kilometragem < 0) {
                throw new IllegalArgumentException("Kilometragem não pode ser negativa");
            }
            else {
                this.kilometragem = kilometragem;
            }
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar a kilometragem do veículo");
        }

    }
    public void excluirVeiculo(){
        this.marca = null;
        this.modelo = null;
        this.ano = null;
        this.placa = null;
        this.cor = null;
        this.kilometragem = null;
        this.proprietario = null;
        this.chassi = null;
    }
    public void alterarCor(String cor){
        this.cor = cor;
    }

}
