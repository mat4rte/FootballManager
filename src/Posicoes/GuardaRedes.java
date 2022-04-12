package Posicoes;

import java.io.Serializable;
import java.util.ArrayList;

public class GuardaRedes extends Futeboleiro implements Serializable {


    private int elasticidade;
    private int mergulho;

    public GuardaRedes(){
        super();
        this.elasticidade = 0;
        this.mergulho = 0;
    }

    public GuardaRedes(GuardaRedes gr){
        super(gr);
        this.elasticidade = gr.elasticidade;
        this.mergulho = gr.mergulho;
    }

    public GuardaRedes(String nome, String num, int vel, int res, int des, int imp, int jc, int rem, int cp, int ela){
        super(num, nome, vel, res, des, imp, jc, rem, cp, new ArrayList<>());
        int max = 20;
        int min = -20;
        this.elasticidade = ela;
        int soma = ((int) super.overall() + ela)/2;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        this.mergulho = soma + random_int;
    }

    public GuardaRedes(String num, String nome, int vel, int res, int des, int imp, int jc, int rem, int cp, ArrayList<String> eqs, int pos, int mer){
        super(num, nome, vel, res, des, imp, jc, rem, cp, eqs);
        this.mergulho = mer;
        this.elasticidade = pos;
    }
    public String toString() {
        return "\nPos: Guarda-Redes " +
                super.toString() +
                "\nPosicionamento = " + elasticidade +
                "\nMergulho = " + mergulho;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GuardaRedes that = (GuardaRedes) o;
        return this.equals(that) && elasticidade == that.elasticidade && mergulho == that.mergulho;
    }

    public GuardaRedes clone() {
        return new GuardaRedes(this);
    }


    public int getElasticidade() {
        return elasticidade;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    public int getMergulho() {
        return mergulho;
    }

    public void setMergulho(int mergulho) {
        this.mergulho = mergulho;
    }


    public double overall(){
        double A = 0.60;
        double B = 0.30;
        double C = 0.10;
        double somaA = getElasticidade() + getMergulho() + getImpulsao();
        double somaB = getJogo_cabeca() + getRemate() + getCapacidade_passe();
        double somaC = getVelocidade() + getResistencia() + getDestreza();
        return Math.round(((somaA/3)*A + (somaB/3)*B + (somaC)/3*C) * 100000d) / 100000d;
    }

    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], campos[1],
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
