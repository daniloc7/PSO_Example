/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_example;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author danil
 */
public class Populacao {

    private Individuo[] populacao;
    private double[] gbestPosition;
    private double gBest;

    public void setgBest(double gBest) {
        this.gBest = gBest;
    }

    public double getgBest() {
        return gBest;
    }

    public double[] getGbestPosition() {
        return gbestPosition;
    }

    public void setGbestPosition(double[] gbestPosition) {
        this.gbestPosition = gbestPosition;
    }

    public Populacao(Individuo[] populacao) {
        this.populacao = populacao;
    }

    public Populacao() {
        populacao = new Individuo[Config.POP];
        for (int i = 0; i < Config.POP; i++) {
            populacao[i] = new Individuo(gerarAleatorio(), gerarAleatorio());
            System.out.println("VALORPOP:" + Arrays.toString(populacao[i].getX()));
        }
    }

    public Individuo[] getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Individuo[] populacao) {
        this.populacao = populacao;
    }

    public final double[] gerarAleatorio() {
        int j = 0;

        double vector[] = new double[Config.DIMENSAO];

        while (j != Config.DIMENSAO) {
            double randomInteger = (Math.random() * ((Config.SCHAFFER_XMAX - (Config.SCHAFFER_XMIN)) + (Config.SCHAFFER_XMIN)));
            vector[j] = randomInteger;
            j++;
        }
        return vector;
    }

    public final void limparPopulacao() {
        Arrays.fill(populacao, null);
    }

}
