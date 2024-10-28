package org.example.dtos;

public class UsuarioDto {

    private Long id;
    private String nome;
    private String endereco;
    private String senha;
    private String email;
    private String dataNascimento;
    private String cpf;
    private String telefone;

    // Construtor padrão necessário para desserialização
    public UsuarioDto() {
    }

//    Construtor alternativo
//    public UsuarioDto(String nome, String email, String cpf, String senha) {
//        this.nome = nome;
//        this.email = email;
//        this.cpf = cpf;
//        this.senha = senha;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
}
