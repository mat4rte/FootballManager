package Posicoes;

import java.io.Serializable;
import java.util.ArrayList;

public class Futeboleiro implements Serializable {

    private String numero;
    private String nome;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogo_cabeca;
    private int remate;
    private int capacidade_passe;
    private ArrayList<String> equipas = new ArrayList<String>();


    public Futeboleiro(){
        this.numero = "";
        this.nome = "";
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogo_cabeca = 0;
        this.remate = 0;
        this.capacidade_passe = 0;
        this.equipas = new ArrayList<String>();
    }

    public Futeboleiro(String num, String nome, int vel, int res, int des, int imp, int jc, int rem, int cp, ArrayList<String> eqs){

        this.numero = num;
        this.nome = nome;
        this.velocidade = vel;
        this.resistencia = res;
        this.destreza = des;
        this.impulsao = imp;
        this.jogo_cabeca = jc;
        this.remate = rem;
        this.capacidade_passe = cp;
        this.equipas = eqs;
    }

    public Futeboleiro(Futeboleiro player){
        this.numero = player.numero;
        this.nome = player.nome;
        this.velocidade = player.velocidade;
        this.resistencia = player.resistencia;
        this.destreza = player.destreza;
        this.impulsao = player.impulsao;
        this.jogo_cabeca = player.jogo_cabeca;
        this.remate = player.remate;
        this.capacidade_passe = player.capacidade_passe;
        this.equipas = player.equipas;
    }

    //Clone, toString, equals


    public Futeboleiro clone() {
        return new Futeboleiro(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.     append("\nNumero: " + this.numero).
                append("\nNome: " + this.nome).
                append("\nHistórico Equipas: " + this.equipas.toString()).
                append("\nVelocidade: " + this.velocidade).
                append("\nResistencia: " + this.resistencia).
                append("\nDestreza: " + this.destreza).
                append("\nImpulsao: " + this.impulsao).
                append("\nJogo de Cabeça: " + this.jogo_cabeca).
                append("\nRemate: " + this.remate).
                append("\nCapacidade de Passe: " + this.capacidade_passe).
                append("\nHabilidade: " + this.overall());
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Futeboleiro player = (Futeboleiro) o;
        return  this.numero == player.numero &&
                this.nome == player.nome &&
                this.velocidade == player.velocidade &&
                this.resistencia == player.resistencia &&
                this.destreza == player.destreza &&
                this.impulsao == player.impulsao &&
                this.jogo_cabeca == player.jogo_cabeca &&
                this.remate == player.remate &&
                this.capacidade_passe == player.capacidade_passe &&
                this.equipas.equals(player.equipas);
    }

    //Getters, Setters

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public int getJogo_cabeca() {
        return jogo_cabeca;
    }

    public void setJogo_cabeca(int jogo_cabeca) {
        this.jogo_cabeca = jogo_cabeca;
    }

    public int getRemate() {
        return remate;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public int getCapacidade_passe() {
        return capacidade_passe;
    }

    public ArrayList<String> getEquipas() {
        return equipas;
    }

    public void setEquipas(ArrayList<String> equipas) {
        this.equipas = equipas;
    }

    public void setCapacidade_passe(int capacidade_passe) {
        this.capacidade_passe = capacidade_passe;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String num) {
        this.numero = num;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Métodos

    public  double overall(){
        int soma = this.velocidade + this.resistencia + this.destreza + this.impulsao + this.jogo_cabeca + this.remate + this.capacidade_passe;
        return soma/7;
    }

    public String getPos(){
        if(this instanceof GuardaRedes)
            return "GR";
        if(this instanceof Defesa)
            return "DEF";
        if(this instanceof Lateral)
            return "LAT";
        if(this instanceof Medio)
            return "MED";
        if(this instanceof Avancado)
            return "AVA";
        return null;
    }

    /*

     */


}