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
public class Individuo {

    private double[] x;
    private double[] pbestPosition;
    private double[] velocidade;
    private double[] inertiaTerm;
    private double[] cognitiveTerm;
    private double[] socialTerm;
    private double aptidao;
    private double pbest;

    public double getAptidao() {
        return aptidao;
    }

    public double getVelocidade(int i) {
        return velocidade[i];
    }

    public void setVelocidade(int i, double velocidade) {
        this.velocidade[i] = velocidade;
    }

    public double getValoresX(int i) {
        return x[i];
    }

    public void setValoresX(int i, double maior) {
        this.x[i] = maior;
    }

    public double getPbest() {
        return pbest;
    }

    public void setPbest(double pbest) {
        this.pbest = pbest;
    }

    public double[] getPbestPosition() {
        return pbestPosition;
    }

    public void setPbestPosition(double[] pbestPosition) {
        this.pbestPosition = pbestPosition;
    }

    public void setX(double[] x, double[] velocidade) {
        this.x = x;
        this.velocidade = velocidade;
    }

    public Individuo(double[] x, double[] velocidade) {
        this.x = x;
        this.velocidade = velocidade;
        this.inertiaTerm = new double[Config.DIMENSAO];
        this.cognitiveTerm = new double[Config.DIMENSAO];
        this.socialTerm = new double[Config.DIMENSAO];
    }

    public double[] getX() {
        return x;
    }

    public double retornaFitness(String parametro) {
        double funcao = 0;
        int i = 0;

        if (parametro.equals("Rosenbrock")) {
            for (i = 0; i < Config.DIMENSAO - 1; i++) {
                double termo1 = 100 * Math.pow((x[i + 1] - x[i] * x[i]), 2);
                double termo2 = Math.pow((1 - x[i]), 2);
                funcao += termo1 + termo2;
            }
        } else if (parametro.equals("Sphere")) {
            while (i != Config.DIMENSAO) {
                funcao += (x[i] * x[i]);
                i++;
            }
        } else if (parametro.equals("Rastrigin")) {
            while (i != Config.DIMENSAO) {
                funcao += ((x[i] * x[i]) - 10 * Math.cos(2 * Math.PI * x[i]) + 10);
                i++;
            }
        } else if (parametro.equals("Griewank")) {
            for (i = 0; i < Config.DIMENSAO - 1; i++) {
                funcao += ((x[i] * x[i]) / 4000) - Math.cos(x[i] / Math.sqrt(x[i + 1])) + 1;
            }
        } else if (parametro.equals("Schaffer")) {
            while (i != Config.DIMENSAO) {
                double termo1 = 0.5 - Math.pow(Math.sin(Math.pow(x[i], 2)), 2);
                double termo2 = 1 / Math.pow(1.0 + (0.001 * Math.pow(x[i], 2)), 2);

                //numero negativo, a formula esta incorreta . Nao é a mesma do artigo.
                if (funcao < 0) {
                    funcao += (termo1 * termo2 * -1);
                } else {
                    funcao += termo1 * termo2;
                }
                i++;
            }
        }

        setAptidao(funcao);
        return funcao;
    }

    public void updateVelocity(double[] pbestPosition, double[] gbestPosition, double W) {
        for (int i = 0; i < Config.DIMENSAO; i++) {
            double r1 = Math.random(); // 0 ate 1
            double r2 = Math.random();
            if (W <= 0.4) {
                W = 0.4;
            }
            inertiaTerm[i] = W * velocidade[i];
            cognitiveTerm[i] = Config.C1 * r1 * (pbestPosition[i] - x[i]);
            socialTerm[i] = Config.C2 * r2 * (gbestPosition[i] - x[i]);

            velocidade[i] = inertiaTerm[i] + (cognitiveTerm[i]) + (socialTerm[i]);
        }
    }

    public void updatePosition() {
        for (int i = 0; i < Config.DIMENSAO; i++) {
            x[i] += velocidade[i];
        }
        retornaFitness("Schaffer");
    }

    public void setAptidao(double aptidao) {
        this.aptidao = aptidao;
    }

    public double[] getInertiaTerm() {
        return inertiaTerm;
    }

    public void setInertiaTerm(double[] inertiaTerm) {
        this.inertiaTerm = inertiaTerm;
    }

    public double[] getCognitiveTerm() {
        return cognitiveTerm;
    }

    public void setCognitiveTerm(double[] cognitiveTerm) {
        this.cognitiveTerm = cognitiveTerm;
    }

    public double[] getSocialTerm() {
        return socialTerm;
    }

    public void setSocialTerm(double[] socialTerm) {
        this.socialTerm = socialTerm;
    }

}
