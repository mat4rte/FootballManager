package Posicoes;

import java.io.Serializable;
import java.util.ArrayList;

public class Medio extends Futeboleiro implements Serializable {


    private int recuperacao_bola;
    private int visao_de_jogo;

    public Medio(){
        super();
        this.recuperacao_bola = 0;
        this.visao_de_jogo = 0;
    }

    public Medio(Medio med){
        super(med);
        this.recuperacao_bola = med.recuperacao_bola;
        this.visao_de_jogo = med.visao_de_jogo;
    }

    public Medio(String nome, String num, int vel, int res, int des, int imp, int jc, int rem, int cp, int rec){
        super(num, nome, vel, res, des, imp, jc, rem, cp, new ArrayList<String>());
        int soma = ((int) super.overall() + rec)/2;
        int max = 100;
        int min = 50;
        this.recuperacao_bola = rec;
        this.visao_de_jogo = ((int)Math.floor(Math.random()*(max-min+1)+min) + soma)/2;
    }

    public Medio(String num, String nome, int vel, int res, int des, int imp, int jc, int rem, int cp, ArrayList<String> eqs, int rec, int vis){
        super(num, nome, vel, res, des, imp, jc, rem, cp, eqs);
        this.recuperacao_bola = rec;
        this.visao_de_jogo = vis;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Medio medio = (Medio) o;
        return this.equals(medio) && recuperacao_bola == medio.recuperacao_bola && visao_de_jogo == medio.visao_de_jogo;
    }


    public String toString() {
        return "\nPos: Medio" +
                super.toString() +
                "\nRecuperacao bola = " + recuperacao_bola +
                "\nVisao de jogo = " + visao_de_jogo;
    }

    public Medio clone() {
        return new Medio(this);
    }

    public int getRecuperacao_bola() {
        return recuperacao_bola;
    }

    public void setRecuperacao_bola(int recuperacao_bola) {
        this.recuperacao_bola = recuperacao_bola;
    }

    public int getVisao_de_jogo() {
        return visao_de_jogo;
    }

    public void setVisao_de_jogo(int visao_de_jogo) {
        this.visao_de_jogo = visao_de_jogo;
    }


    public double overall(){
        double A = 0.60;
        double B = 0.30;
        double C = 0.10;
        double somaA = getCapacidade_passe() + getVisao_de_jogo() + getRecuperacao_bola();
        double somaB = getRemate() + getDestreza();
        double somaC = getVelocidade() + getResistencia() + getImpulsao() + getJogo_cabeca();
        return Math.round(((somaA/3)*A + (somaB/2)*B + (somaC)/4*C) * 100000d) / 100000d;
    }

    public static Medio parse(String input){
        String[] campos = input.split(",");
        return new Medio(campos[0], campos[1],
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

}
