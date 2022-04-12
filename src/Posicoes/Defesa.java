package Posicoes;

import java.io.Serializable;
import java.util.ArrayList;

public class Defesa extends Futeboleiro implements Serializable {

    private int desarme;
    private int forca;

    public Defesa(){
        super();
        this.desarme = 0;
        this.forca = 0;
    }

    public Defesa(Defesa def){
        super(def);
        this.desarme = def.desarme;
        this.forca = def.forca;
    }

    public Defesa(String nome, String num, int vel, int res, int des, int imp, int jc, int rem, int cp){
        super(num, nome, vel, res, des, imp, jc, rem, cp, new ArrayList<String>());
        int soma = (int) super.overall();
        int max = 100;
        int min = 50;
        this.desarme = ((int)Math.floor(Math.random()*(max-min+1)+min) + soma)/2;
        this.forca = ((int)Math.floor(Math.random()*(max-min+1)+min) + soma)/2;
    }

    public Defesa(String num, String nome, int vel, int res, int des, int imp, int jc, int rem, int cp, ArrayList<String> eqs, int desa, int fo){
        super(num, nome, vel, res, des, imp, jc, rem, cp, eqs);
        this.desarme = desa;
        this.forca = fo;
    }

    public String toString() {
        return "\nPos: Defesa" +
                super.toString() +
                "\nDesarme = " + desarme +
                "\nForca = " + forca;
    }

    public Defesa clone() {
        return new Defesa(this);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Defesa defesa = (Defesa) o;
        return this.equals(defesa) && desarme == defesa.desarme && forca == defesa.forca;
    }

    public int getDesarme() {
        return desarme;
    }

    public void setDesarme(int desarme) {
        this.desarme = desarme;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public double overall(){
        double A = 0.60;
        double B = 0.30;
        double C = 0.10;
        double somaA = (getJogo_cabeca() + getDesarme() + getForca());
        double somaB = (getVelocidade() + getResistencia() + getImpulsao() + getCapacidade_passe());
        double somaC =  (getDestreza() + getRemate());
        return Math.round(((somaA/3)*A + (somaB/4)*B + (somaC)/2*C) * 100000d) / 100000d;
    }

    public static Defesa parse(String input){
        String[] campos = input.split(",");
        return new Defesa(campos[0], campos[1],
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }

}
