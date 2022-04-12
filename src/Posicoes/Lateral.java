package Posicoes;

import java.util.ArrayList;

public class Lateral extends Futeboleiro {


    private int cruzamento;
    private int aceleracao;

    public Lateral(){
        super();
        this.cruzamento = 0;
        this.aceleracao = 0;
    }

    public Lateral(Lateral lr){
        super(lr);
        this.cruzamento = lr.cruzamento;
        this.aceleracao = lr.aceleracao;
    }

    public Lateral(String nome, String num, int vel, int res, int des, int imp, int jc, int rem, int cp, int cruz){
        super(num, nome, vel, res, des, imp, jc, rem, cp, new ArrayList<String>());
        int soma = ((int) super.overall() + cruz)/2;
        int max = 100;
        int min = 50;
        this.cruzamento = cruz;
        this.aceleracao = ((int)Math.floor(Math.random()*(max-min+1)+min) + soma)/2;
    }

    public Lateral(String num, String nome, int vel, int res, int des, int imp, int jc, int rem, int cp, ArrayList<String> eqs, int ace, int cruz){
        super(num, nome, vel, res, des, imp, jc, rem, cp, eqs);
        this.cruzamento = ace;
        this.aceleracao = cruz;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lateral lateral = (Lateral) o;
        return this.equals(lateral) && cruzamento == lateral.cruzamento && aceleracao == lateral.aceleracao;
    }

    public String toString() {
        return "\nPos: Lateral" +
                super.toString() +
                "\nCruzamento = " + cruzamento +
                "\nAceleracao = " + aceleracao ;
    }

    public Lateral clone() {
        return new Lateral(this);
    }


    public int getCruzamento() {
        return cruzamento;
    }

    public void setCruzamento(int cruzamento) {
        this.cruzamento = cruzamento;
    }

    public int getAceleracao() {
        return aceleracao;
    }

    public void setAceleracao(int aceleracao) {
        this.aceleracao = aceleracao;
    }

    public double overall(){
        double A = 0.60;
        double B = 0.30;
        double C = 0.10;
        double somaA = getVelocidade() + getCruzamento() + getAceleracao();
        double somaB = getResistencia() + getDestreza() +  + getCapacidade_passe();
        double somaC = getImpulsao() + getJogo_cabeca() + getRemate();
        return Math.round(((somaA/3)*A + (somaB/3)*B + (somaC)/3*C) * 100000d) / 100000d;
    }

    public static Lateral parse(String input){
        String[] campos = input.split(",");
        return new Lateral(campos[0], campos[1],
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
