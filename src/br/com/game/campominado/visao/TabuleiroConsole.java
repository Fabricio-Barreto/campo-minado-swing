package br.com.game.campominado.visao;

import br.com.game.campominado.exception.ExplosaoException;
import br.com.game.campominado.exception.SairException;
import br.com.game.campominado.model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;

            while (continuar) {
                cicloDoJogo();

                System.out.println("Outra partida? (S/N) ");
                String resposta = entrada.nextLine();

                if("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }

        } catch (SairException e) {
            System.out.println("Até mais!!");
        } finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {

            while(!tabuleiro.objectivoAlcancado()) {
                System.out.println(tabuleiro);

                String digitado = capturarVAlorDigitado("Digite (x, y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();

                digitado = capturarVAlorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");

                if("1".equals(digitado)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equals(digitado)) {
                    tabuleiro.marcar(xy.next(), xy.next());
                }
            }

            System.out.println(tabuleiro);
            System.out.println("Você ganhou!!!");
        } catch(ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você perdeu!");
        }
    }

    private String capturarVAlorDigitado(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }

        return digitado;
    }
}
