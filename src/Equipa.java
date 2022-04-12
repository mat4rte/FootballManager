import Posicoes.Futeboleiro;

import java.io.Serializable;
import java.util.*;

public class Equipa implements Serializable {


    private String nome;
    private Map<String, Futeboleiro> equipa;

    public Equipa(){
        this.nome = "";
        this.equipa = new HashMap<>();
    }

    public Equipa(String nome){
        this.nome = nome;
        this.equipa = new HashMap<>();
    }

    public Equipa(String n, Map<String, Futeboleiro> eq){
        this.nome = n;
        //this.equipa = eq.values().stream().collect(Collectors.toMap(Posicoes.Futeboleiro :: getId, Posicoes.Futeboleiro::clone));
        this.equipa = eq;
    }

    public Equipa(Equipa eq){
        this.nome = eq.nome;
        //this.equipa = eq.equipa.values().stream().collect(Collectors.toMap(Posicoes.Futeboleiro :: getId, Posicoes.Futeboleiro::clone));
        this.equipa = eq.equipa;
    }

    public Equipa clone() {
        return new Equipa(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
                sb.append("\nEquipa: ")
                .append(getNome())
                .append("\nHabilidade: ")
                .append(overallEquipa(getEquipa().keySet()))
                .append("\nEquipa: ");

        for(Futeboleiro f : getEquipa().values()){
                     sb.append(f.getNome())
                    .append("(")
                    .append(f.getNumero())
                    .append(",").append(f.getPos()).append(")\n");
        }

        /*return "\nEquipa: " + getNome() +
                "\nHabilidade: " + overallEquipa() +
                "\nNome = " + nome +
                "\nEquipa = " + equipa.values().stream().map(Posicoes.Futeboleiro::getNome).collect(Collectors.toList()) +
                "\nEquipa : " + equipa.values().stream().map(Posicoes.Futeboleiro::getNumero)..collect(Collectors.toList());
                */
         return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipa equipa1 = (Equipa) o;
        return Objects.equals(nome, equipa1.nome) && Objects.equals(equipa, equipa1.equipa);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Futeboleiro> getEquipa() {
        return this.equipa;
        //return this.equipa.values().stream().collect(Collectors.toMap(Posicoes.Futeboleiro :: getId, Posicoes.Futeboleiro::clone));
    }

    public void setEquipa(Map<String, Futeboleiro> equipa) {
        this.equipa = equipa;
        //this.equipa = equipa.values().stream().collect(Collectors.toMap(Posicoes.Futeboleiro :: getId, Posicoes.Futeboleiro::clone));
    }

    public void addJogador(Futeboleiro j1){
        this.equipa.putIfAbsent(j1.getNumero(), j1);
    }

    public Futeboleiro removeJogador(String j1){
        if (equipa.containsKey(j1))
            return this.equipa.remove(j1);
        return null;
    }

    //adiciona jogador
    //remove jogador
    //getjogadores
    //setjogadores
    //overall (equipa)

    public double overallEquipa(Set<String> jogs){
        double total = 0;
        for(String num : jogs){
            total += getEquipa().get(num).overall();
        }
        return Math.round(total/equipa.values().size() * 100000d) / 100000d;
    }



    public String mostraJogadores(){
        StringBuilder sb = new StringBuilder();
        for (Futeboleiro f : getEquipa().values()){
            sb.append(f.getNumero() + " - " + f.getNome());
            //sb.append(f.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

}
