/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_example;

/**
 *
 * @author danil
 */
public class Config {

    // tamanho da população
    public static final int POP = 30;

    public static final int FATOR_REPETIBILIDADE = 10000;

    //Cada individuo vai ter um valor de dimensão
    //A aptidao vai ser a soma das dimensoes ao quadrado, ou outra funcao
    public static int DIMENSAO = 30;

    public static final double C1 = 2;

    public static final double C2 = 2;

    public static final double W = 0.9;

    //SPHERE
    public static final int SPHERE_XMAX = 10000;
    public static final int SPHERE_XMIN = -10000;

    //ROSENBROCK
    public static final double ROSENBROCK_XMAX = 30;
    public static final double ROSENBROCK_XMIN = -30;

    //RASTRIGIN
    public static final double RASTRIGIN_XMAX = 5.12;
    public static final double RASTRIGIN_XMIN = -5.12;

    //GRIEWANK
    public static final double GRIEWANK_XMAX = 600;
    public static final double GRIEWANK_XMIN = -600;

    //SCHAFFER
    public static final double SCHAFFER_XMAX = 100;
    public static final double SCHAFFER_XMIN = -100;

}
