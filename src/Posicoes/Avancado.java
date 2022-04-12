package Posicoes;

import java.io.Serializable;
import java.util.ArrayList;

public class Avancado extends Futeboleiro implements Serializable {

    private int finalizacao;
    private int drible;

    public Avancado(){
        super();
        this.finalizacao = 0;
        this.drible = 0;
    }

    public Avancado(Avancado ava){
        super(ava);
        this.finalizacao = ava.finalizacao;
        this.drible = ava.drible;
    }

    public Avancado(String num, String nome, int vel, int res, int des, int imp, int jc, int rem, int cp, ArrayList<String> eqs, int fin, int dri){
        super(num, nome, vel, res, des, imp, jc, rem, cp, eqs);
        this.finalizacao = fin;
        this.drible = dri;
    }


    public Avancado(String nome, String num, int vel, int res, int des, int imp, int jc, int rem, int cp){
        super(num, nome, vel, res, des, imp, jc, rem, cp, new ArrayList<String>());
        int soma = (int) super.overall();
        int max = 100;
        int min = 50;
        this.finalizacao = ((int)Math.floor(Math.random()*(max-min+1)+min) + soma)/2;
        this.drible = ((int)Math.floor(Math.random()*(max-min+1)+min) + soma)/2;
    }


    public String toString() {
        return "\nPos: Avancado" +
                super.toString() +
                "\nFinalizacao = " + finalizacao +
                "\nDrible = " + drible;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Avancado avancado = (Avancado) o;
        return this.equals(avancado) && finalizacao == avancado.finalizacao && drible == avancado.drible;
    }

    public Avancado clone() {
        return new Avancado(this);
    }


    public int getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(int finalizacao) {
        this.finalizacao = finalizacao;
    }

    public int getDrible() {
        return drible;
    }

    public void setDrible(int drible) {
        this.drible = drible;
    }


    public double overall(){
        double A = 0.60;
        double B = 0.30;
        double C = 0.10;
        double somaA = getJogo_cabeca() + getRemate() + getDrible() + getFinalizacao();
        double somaB = getVelocidade() + getImpulsao() + getResistencia();
        double somaC = getDestreza() + getCapacidade_passe();
        return Math.round(((somaA/4)*A + (somaB/3)*B + (somaC)/2*C) * 100000d) / 100000d;
    }

    public static Avancado parse(String input){
        String[] campos = input.split(",");
        return new Avancado(campos[0], campos[1],
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }

}
