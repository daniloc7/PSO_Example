
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author danil
 */
public class PSO_Example {

    public static void main(String[] args) {

        String atual = "Schaffer";
        int rodada = 0;
        int[] iteracoesRodadas = new int[20];
        DecimalFormat f = new DecimalFormat("#.####");

        outerLoop:
        while (rodada != 20) {
            rodada++;
            // Instancia populacao 
            Populacao populacao = new Populacao();
            ArrayList<Double> menorValor = new ArrayList<Double>();
            //SETA O PBEST DE CADA INDIVIDUO
            for (Individuo individuo : populacao.getPopulacao()) {
                menorValor.add(individuo.retornaFitness(atual));
                individuo.retornaFitness(atual); //seta o getAptidao
                individuo.setPbest(individuo.getAptidao());
                individuo.setPbestPosition(individuo.getX().clone());
            }

            //SETA O GBEST JA NA PRIMEIRA EXECUCAO
            for (Individuo individuo : populacao.getPopulacao()) {
                if (individuo.getAptidao() == Collections.min(menorValor)) {
                    populacao.setGbestPosition(individuo.getX().clone());
                }
            }

            populacao.setgBest(Collections.min(menorValor));

            int iteracao = 0;
            double w = Config.W;

            for (iteracao = 0; iteracao < Config.FATOR_REPETIBILIDADE; iteracao++) {
//            while (iteracao < Config.FATOR_REPETIBILIDADE) {
                for (Individuo individuo : populacao.getPopulacao()) {
                    //configurar individualmente para cada funcao
                    for (int k = 0; k < Config.DIMENSAO; k++) {
                        if (individuo.getValoresX(k) > Config.SCHAFFER_XMAX) {
                            individuo.setValoresX(k, Config.SCHAFFER_XMAX); //30
                        } else if (individuo.getValoresX(k) < Config.SCHAFFER_XMIN) { //-50
                            individuo.setValoresX(k, Config.SCHAFFER_XMIN);//-30
                        }
//

                        if (individuo.getVelocidade(k) > Config.SCHAFFER_XMAX) {
                            individuo.setVelocidade(k, Config.SCHAFFER_XMAX);
                        } else if (individuo.getVelocidade(k) < Config.SCHAFFER_XMIN) { //-50
                            individuo.setVelocidade(k, Config.SCHAFFER_XMIN);//-30
                        }
                    }
                    if (populacao.getgBest() < 0.01) { //0.00001 para schaffer, 100 para rosenbrock e rastrigin, 0.01 para sphere, 0.05 para griewank, 
                        System.out.println("Melhor solução encontrada(posicao gbest): " + Arrays.toString(populacao.getGbestPosition()));
                        System.out.println("Valor da aptidão do gbest: " + (f.format(populacao.getgBest())));

                        iteracoesRodadas[rodada] = iteracao;
                        Random random = new Random();
//                        populacao.limparPopulacao();
                        continue outerLoop;
                    } else if (iteracao > 9999) {
//                        Random random = new Random();
//                        iteracoesRodadas[rodada - 1] = random.nextInt(300) + 500;
//                        iteracoesRodadas[rodada - 1] = 10000;
                        iteracoesRodadas[rodada - 1] = iteracao;
//                        populacao.limparPopulacao();
                        continue outerLoop;
                    }
//
                    // Atualiza o pbest se a aptidão atual for melhor
                    if (individuo.getPbest() > individuo.retornaFitness(atual)) {
                        individuo.setPbestPosition(individuo.getX().clone());
//                        System.out.println("VALOR ANTIGO: " + (f.format(individuo.getPbest())) + " VALOR ATT: " + (f.format(individuo.retornaFitness("Sphere"))));
                        individuo.setPbest(individuo.getAptidao());
                    }
                    // Atualiza gBest 
                    if (populacao.getgBest() > individuo.getAptidao()) {
                        populacao.setgBest(individuo.getAptidao());
                        populacao.setGbestPosition(individuo.getX().clone());
                    }
                    individuo.updateVelocity(individuo.getPbestPosition(), populacao.getGbestPosition(), w);
                    individuo.updatePosition();
                    System.out.println("----------------------------------------------##-----------------------------------------------------");

                }
                w = w - (0.5 / Config.FATOR_REPETIBILIDADE);
            }

        }

        System.out.println("Rodadas: " + Arrays.toString(iteracoesRodadas));

        try {
            FileWriter writer = new FileWriter("rodadas_teste_schaffer_20.txt");
            BufferedWriter bw = new BufferedWriter(writer);

            for (int i = 0; i < iteracoesRodadas.length; i++) {
                bw.write(String.valueOf(iteracoesRodadas[i]));
                if (i != iteracoesRodadas.length - 1) {
                    bw.write(",");
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
