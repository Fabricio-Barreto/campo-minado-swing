package br.com.game.campominado;

import br.com.game.campominado.model.Tabuleiro;
import br.com.game.campominado.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6,6,3);
        new TabuleiroConsole(tabuleiro);
    }
}
