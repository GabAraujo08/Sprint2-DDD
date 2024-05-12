package org.example.diagnostico;

public class Diagnostico {
    private String descricao;
    private Boolean colisao;
    private String informacaoColisao;
    private String devolutiva;
    private Integer idDiagnostico;

    public Diagnostico(String descricao, Boolean colisao, String informacaoColisao, String devolutiva, Integer idDiagnostico) {
        this.descricao = descricao;
        this.colisao = colisao;
        this.informacaoColisao = informacaoColisao;
        this.devolutiva = devolutiva;
        this.idDiagnostico = idDiagnostico;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getColisao() {
        return colisao;
    }

    public String getInformacaoColisao() {
        return informacaoColisao;
    }

    public String getDevolutiva() {
        return devolutiva;
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }


}
