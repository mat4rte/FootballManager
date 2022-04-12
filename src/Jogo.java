import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Jogo implements Serializable {
    private String equipaCasa;
    private String equipaFora;
    private int golosCasa;
    private int golosFora;
    private LocalDate date;
    private List<String> jogadoresCasa;
    private List<String> jogadoresFora;
    Map<String, String> substituicoesCasa = new HashMap<>();
    Map<String, String> substituicoesFora = new HashMap<>();


    public Jogo(){
        equipaCasa = "";
        equipaFora = "";
        golosCasa = 0;
        golosFora = 0;
        date = LocalDate.now();
        jogadoresCasa = new ArrayList<>();
        jogadoresFora = new ArrayList<>();
        substituicoesCasa = new HashMap<>();
        substituicoesFora = new HashMap<>();
    }

    public Jogo(String eq1, String eq2){
        this.equipaCasa = eq1;
        this.equipaFora = eq2;
    }

    public Jogo(String eqCasa, String eqFora, List<String> jogCasa, List<String> jogFora, Map<String, String> subCasa, Map<String, String> subFora){
        equipaCasa = eqCasa;
        equipaFora = eqFora;
        golosCasa = 0;
        golosFora = 0;
        date = LocalDate.now();
        jogadoresCasa = jogCasa;
        jogadoresFora = jogFora;
        substituicoesCasa = subCasa;
        substituicoesFora = subFora;
    }

    public Jogo (String ec, String ef, int gc, int gf, LocalDate d,  List<String> jc,  List<String> jf, Map<String, String> sc, Map<String, String> sf){
        equipaCasa = ec;
        equipaFora = ef;
        golosCasa = gc;
        golosFora = gf;
        date = d;
        jogadoresCasa = new ArrayList<>(jc);
        jogadoresFora = new ArrayList<>(jf);
        substituicoesCasa = new HashMap<>(sc);
        substituicoesFora = new HashMap<>(sf);
    }

    public Jogo(Jogo j){
        equipaCasa = j.getEquipaCasa();
        equipaFora = j.getEquipaFora();
        golosCasa = j.getGolosCasa();
        golosFora = j.getGolosFora();
        date = j.getDate();
        jogadoresCasa = j.getJogadoresCasa();
        jogadoresFora = j.getJogadoresFora();
        substituicoesCasa = j.getSubstituicoesCasa();
        substituicoesFora = j.getSubstituicoesFora();
    }

    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<String> jc = new ArrayList<>();
        List<String> jf = new ArrayList<>();
        Map<String, String> subsC = new HashMap<>();
        Map<String, String> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(campos[i]);
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(sub[0], sub[1]);
        }
        for (int i = 19; i < 30; i++){
            jf.add(campos[i]);
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(sub[0], sub[1]);
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, jf, subsC, subsF);
    }

    public Jogo clone() {
        return new Jogo(this);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Jogo: ").append(equipaCasa).
                append("  ").
                append(getGolosCasa()).append(" - ").
                append(getGolosFora()).append("  ").append(equipaFora).
                append("\nData: ").append(date).
                append("\nJogadores Casa: ").append(jogadoresCasa).
                append("\nJogadores Fora: ").append(jogadoresFora).
                append("\nSubstituicoes Casa: ");
        for(String num : substituicoesCasa.keySet()){
            sb.append(num).append("->").append(substituicoesCasa.get(num)).append(" ");
        }
        sb.append("\nSubstituicoes Fora: ");
        for(String num : substituicoesFora.keySet()){
            sb.append(num).append("->").append(substituicoesFora.get(num)).append(" ");
        }
        return sb.toString();
    }

    //get e set
    public String getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(String equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public String getEquipaFora() {
        return equipaFora;
    }

    public void setEquipaFora(String equipaFora) {
        this.equipaFora = equipaFora;
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getJogadoresCasa() {
        return jogadoresCasa;
    }

    public void setJogadoresCasa(List<String> jogadoresCasa) {
        this.jogadoresCasa = jogadoresCasa;
    }

    public List<String> getJogadoresFora() {
        return jogadoresFora;
    }

    public void setJogadoresFora(List<String> jogadoresFora) {
        this.jogadoresFora = jogadoresFora;
    }

    public Map<String, String> getSubstituicoesCasa() {
        return substituicoesCasa;
    }

    public void setSubstituicoesCasa(Map<String, String> substituicoesCasa) {
        this.substituicoesCasa = substituicoesCasa;
    }

    public Map<String, String> getSubstituicoesFora() {
        return substituicoesFora;
    }

    public void setSubstituicoesFora(Map<String, String> substituicoesFora) {
        this.substituicoesFora = substituicoesFora;
    }

    public void fazerSubstituicoes(Map<String, String> sub, List<String> equipa){

        for(String jogE : getSubstituicoesCasa().keySet()){
            getJogadoresCasa().remove(getSubstituicoesCasa().get(jogE));
            getJogadoresCasa().add(jogE);
        }
    }

    public void realizarJogo(double overCasa, double overFora) throws InterruptedException {

        Random randAtaque = new Random();
        int golo = 15;
        ArrayList<String> momentoDeJogo = new ArrayList<>();


        for(int parte = 1; parte <= 2; parte++){

            for(int momento = 1; momento <= 5; momento++){

                int ataque = randAtaque.nextInt((int) overCasa + (int) overFora) + 1;
                momentoDeJogo = obterMomento(golo);
                golo = Integer.parseInt(momentoDeJogo.get(2));
                if(ataque >= overCasa){
                    //ataque da equipa de fora
                    escreverMomento(momentoDeJogo, getEquipaFora(), getEquipaCasa());
                }
                else if(ataque < overFora){
                    //ataque da equipa da casa
                    escreverMomento(momentoDeJogo, getEquipaCasa(), getEquipaFora());
                }
                TimeUnit.MILLISECONDS.sleep(200);
            }
            if(parte == 1){
                System.out.println("Intervalo!");
                /*
                System.out.println("Substituições Realizadas!");
                fazerSubstituicoes(getSubstituicoesCasa(), new ArrayList<>(getJogadoresCasa()));
                fazerSubstituicoes(getSubstituicoesFora(), new ArrayList<>(getJogadoresFora()));
                 */
            }
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("Fim do Jogo");
        System.out.println(getEquipaCasa() +  " " + getGolosCasa() + " - " + getGolosFora() + " " + getEquipaFora());
    }

    public ArrayList<String> obterMomento(int golo){
        Random randMomento = new Random();
        //Momentos Ata
        int penalti = 2;
        int livre = 25;

        //Momentos Def
        int cartaoAmarelo = 30;
        int cartaoVermelho = 5;

        //Nada
        int nada = 60;

        int momento = randMomento.nextInt(100) + 1;
        ArrayList<String> momentoAtual = new ArrayList<>();

        if (momento <= golo){
            golo = 15; //Reset Valor original
            momentoAtual.add("ATA");
            momentoAtual.add("GOLO");
        }
        else if(momento <= golo + penalti){
            //penalti
            golo = 70;
            momentoAtual.add("ATA");
            momentoAtual.add("PENALTI");
        }
        else if(momento <= golo + penalti + livre){
            //livre
            golo = 40;
            momentoAtual.add("ATA");
            momentoAtual.add("LIVRE");
        }
        else if(momento <= golo + penalti + livre + cartaoAmarelo){
            //amarelo
            golo = 15;
            momentoAtual.add("DEF");
            momentoAtual.add("AMARELO");
        }
        else if(momento <= golo + penalti + livre + cartaoAmarelo + cartaoVermelho) {
            //vermelho
            golo = 15;
            momentoAtual.add("DEF");
            momentoAtual.add("VERMELHO");
        }
        else{
            //nada
            golo = 15;
            momentoAtual.add("NADA");
            momentoAtual.add("NADA");
        }

        momentoAtual.add(String.valueOf(golo));
        return momentoAtual;
    }

    public void escreverMomento(ArrayList<String> momentoDeJogo, String equipaATA, String equipaDEF){
        if(momentoDeJogo.get(0).equals("ATA")){
            System.out.println(momentoDeJogo.get(1) + " de " + equipaATA);
            if(momentoDeJogo.get(1).equals("GOLO")){
                if(equipaATA.equals(getEquipaFora()))
                    setGolosFora(getGolosFora() + 1);
                else{
                    setGolosCasa(getGolosCasa() + 1);
                }
            }
        }

        if(momentoDeJogo.get(0).equals("DEF")){
            System.out.println(momentoDeJogo.get(1) + " de " + equipaDEF);
        }

        if(momentoDeJogo.get(0).equals("NADA"))
            System.out.println("....");

    }
}