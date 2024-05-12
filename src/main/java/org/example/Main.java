package org.example;

import org.example.usuario.Usuario;
import org.example.veiculo.Veiculo;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("João", "Silva", "joao@gmail.com","123456", "01/01/2000", "12345678910", "123456789");
        Veiculo veiculo = new Veiculo("Fiat", "Uno", 2000, "ABC1234", "Preto", 100000, usuario, "123456789");

        System.out.println(veiculo.getProprietario().getNome());
}
}