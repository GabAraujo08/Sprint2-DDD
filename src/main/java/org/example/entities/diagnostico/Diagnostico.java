package org.example.entities.diagnostico;

public class Diagnostico {
    private String descricao;
    private Boolean colisao;
    private String informacaoColisao;
    private String devolutiva;
    private Integer idDiagnostico;

    public Diagnostico(String descricao, Boolean colisao, String informacaoColisao, Integer idDiagnostico) {
        this.descricao = descricao;
        this.colisao = colisao;
        this.informacaoColisao = informacaoColisao;
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
    /*
    public String getDevolutiva() {
        return devolutiva;
    }
    */
    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }
    /*
    public String gerarDevolutiva(){

        Este não é o funcionamento real do método, pois entedno que para realizar este método é necessário a integração com a api de inteligencia artificial, e este método utilizará os dados de colisão, descricao, informacaoColisao pra gerar essa devolutiva, inseri essa execução apenas pra criar os testes

        if (colisao){
            devolutiva = "Colisão detectada";
        }else{
            devolutiva = "Colisão não detectada";
        }
        return devolutiva;
    }
    */

}
