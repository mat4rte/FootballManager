import Posicoes.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Gestao implements Serializable{

    private static Map<String, Equipa> equipas;
    private static List<Jogo> jogos;


    public Gestao(){
        equipas = new HashMap<>();
        jogos = new ArrayList<>();
    }

    public Gestao(Map<String, Equipa> equip){
        equipas = equip;
    }

    public Gestao(Map<String, Equipa> equip, ArrayList<Jogo> jogs){
        equipas = equip;
        jogos = jogs;
    }

    public Gestao(Gestao ge){
        equipas = ge.getEquipas();
        jogos = ge.getJogos();
    }

    public Gestao clone(){
        return new Gestao(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Equipa e : equipas.values()){
            sb.append(e.getNome()).append("\t\t").append("Habilidade: ").append(e.overallEquipa(e.getEquipa().keySet()));
            sb.append("\n");
            //sb.append(e.mostraJogadores());
            //sb.append("\n");
        }

        return sb.toString();
    }

    public Map<String, Equipa> getEquipas(){
        return equipas;
    }

    public void setEquipas(Map<String, Equipa> eq){
        equipas = eq;
    }

    public void setJogos(List<Jogo> jgs){
        jogos = jgs;
    }

    public List<Jogo> getJogos(){
        return jogos;
    }

    public void addJogo(Jogo newJogo){
        if(jogos.isEmpty()){
            jogos = new ArrayList<>();
        }
        jogos.add(newJogo);
    }

    public void novoJogo(String nomeEquipaCasa, String nomeEquipaFora, int golosCasa, int golosFora, LocalDate d, List<String> jogCasa, List<String> jogFora, Map<String, String> subCasa, Map<String, String> subFora) throws InterruptedException {
        Jogo jogo = new Jogo(nomeEquipaCasa, nomeEquipaFora, golosCasa, golosFora, d, jogCasa, jogFora, subCasa, subFora);
        //String vencedor = simulaJogo(jogo);
        double overallCasa = getOverallTitular(getEquipas().get(nomeEquipaCasa), jogCasa);
        double overallFora = getOverallTitular(getEquipas().get(nomeEquipaFora), jogFora);
        jogo.realizarJogo(overallCasa, overallFora);
        getJogos().add(jogo);
    }

    public double getOverallTitular(Equipa equipa, List<String> titulares){
        double over = 0;

        for(String numJ : equipa.getEquipa().keySet()){
            if(titulares.contains(numJ)){
                over += equipa.getEquipa().get(numJ).overall();
            }
        }

        return over/11;
    }

    public void addEquipa(Equipa newEquipa){
        if(equipas.isEmpty()){
            equipas = new HashMap<>();
        }
        equipas.put(newEquipa.getNome() ,newEquipa);
    }

    public boolean transfereJogador(String numA, String numN, String antE, String novaE) throws NumeroIndisponivelException {

        if(equipas.containsKey(antE)){
            if(equipas.containsKey(novaE)){
                try {
                    if (isNumeroOcupado(numN, novaE)) {
                        throw new NumeroIndisponivelException("Numero Já Ocupado!");
                    }
                    else{
                        Futeboleiro f = equipas.get(antE).removeJogador(numA);
                        f.setNumero(numN);
                        f.getEquipas().add(antE);
                        equipas.get(novaE).addJogador(f);
                    }
                }
                catch (NumeroIndisponivelException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNumeroOcupado(String jog, String equipa){
        return getEquipas().get(equipa).getEquipa().containsKey(jog);
    }

    public void removeEquipa(String equip){
        getEquipas().remove(equip);
    }

    public void guardarFicheiroTxt(String nomeFich) throws Exception {

        List<String> linhas;

        String filePath = new File("").getAbsolutePath();

        PrintWriter pw = new PrintWriter(new FileWriter(filePath + "/src/Saves/" + nomeFich + ".txt"));
        for(Equipa e : this.getEquipas().values()){
            pw.println("Equipa:" + e.getNome());
            for(Futeboleiro f: e.getEquipa().values()){
                if(f instanceof GuardaRedes){
                    pw.print("Guarda-Redes:");
                }
                else if(f instanceof Defesa){
                    pw.print("Defesa:");
                }
                else if(f instanceof Lateral){
                    pw.print("Lateral:");
                }
                else if(f instanceof Medio){
                    pw.print("Medio:");
                }
                else if(f instanceof Avancado){
                    pw.print("Avancado:");
                }
                pw.print(f.getNome() + "," + f.getNumero() + "," + f.getVelocidade() + "," + f.getResistencia() + "," + f.getDestreza() + "," + f.getImpulsao() + "," + f.getJogo_cabeca() + "," + f.getRemate() + "," + f.getCapacidade_passe());
                if(f instanceof GuardaRedes){
                    pw.println("," + ((GuardaRedes) f).getElasticidade());
                }
                else if(f instanceof Defesa){
                    pw.append("\n");
                }
                else if(f instanceof Lateral){
                    pw.println("," + ((Lateral) f).getCruzamento());
                }
                else if(f instanceof Medio){
                    pw.println("," + ((Medio) f).getRecuperacao_bola());
                }
                else if(f instanceof Avancado){
                    pw.append("\n");
                }
            }
        }

        for(Jogo j : getJogos()){
            System.out.println(j.toString());
            pw.print("Jogo:" + j.getEquipaCasa() + "," + j.getEquipaFora() + "," + j.getGolosCasa() + "," + j.getGolosFora() + "," + j.getDate());
            //Jogadores Casa
            for(String jog : j.getJogadoresCasa()){
                pw.print(","+ jog);
            }
            //Subs Casa
            for(String sai : j.getSubstituicoesCasa().keySet()){
                String entra = j.getSubstituicoesCasa().get(sai);
                pw.print("," + sai + "->" + entra);
            }

            //Jogadores Fora
            for(String jog : j.getJogadoresFora()){
                pw.print(","+ jog);
            }
            //Subs Fora
            for(String sai : j.getSubstituicoesFora().keySet()){
                String entra = j.getSubstituicoesFora().get(sai);
                pw.print("," + sai + "->" + entra);
            }
            pw.print("\n");
        }
        pw.close();
    }

    public void guardarFicheiroObjeto(String nome) throws IOException {

        String filePath = new File("").getAbsolutePath();
        FileOutputStream fos = new FileOutputStream(filePath + "/src/Saves/" + nome + ".obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
            oos.writeObject(getEquipas());
            oos.writeObject(getJogos());
            oos.flush();
            oos.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void carregarFicheiroObjeto(String nome) throws IOException, ClassNotFoundException {

        String filePath = new File("").getAbsolutePath();
        try {
            FileInputStream fis = new FileInputStream(filePath + "/src/Saves/" + nome);
            ObjectInputStream ois = new ObjectInputStream(fis);
            setEquipas((Map<String, Equipa>) ois.readObject());
            setJogos((List<Jogo>) ois.readObject());
            ois.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}