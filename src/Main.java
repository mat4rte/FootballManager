import Posicoes.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{


    public static void main(String[] args) throws Exception {

        //Menu menu = new Menu();
        Gestao ge = new Gestao();

        do {
            int esc = Inicio();
            switch (esc) {
                case 1:
                    Jogos(ge);
                    break;
                case 2:
                    Gestao(ge);
                    break;
                case 3:
                    menuCarregarFich(ge);
                    break;
                case 4:
                    menuGravarFich(ge);
                    break;
                case 0:
                    return;
            }
        }while(true);
    }

    public static int Inicio(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------FOOTBALL MANAGER--------");
        System.out.println("1 - Jogos");
        System.out.println("2 - Gestão Equipas");
        System.out.println("3 - Carregar Ficheiro");
        System.out.println("4 - Gravar Ficheiro");
        System.out.println("\n0 - Terminar");
        System.out.print("Indique a opcao: ");
        return sc.nextInt();
    }

    public static void Gestao(Gestao ge) throws Exception {

        Scanner sc = new Scanner(System.in);

        do {
            menuGestao();
            int esc = sc.nextInt();
            switch (esc) {
                case 1:
                    ge.addEquipa(criarEquipa());
                    break;
                case 2:
                    menuVerEquipas(ge, true);
                    break;
                case 3:
                    removerEquipa(ge);
                    break;
                case 4:
                    menuAdicionarJogador(ge);
                    break;
                case 5:
                    menuTransferirJogador(ge);
                    break;
                case 6:
                    menuRemoveJogador(ge);
                    break;
                case 0: return;
            }
        }while(true);
    }

    public static void Jogos(Gestao ge) throws JogadorNaoExisteException, EquipaNaoExisteException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean existeJ = mostrarJogos(ge, false);
        if(!ge.getJogos().isEmpty()) {
            System.out.println("\n1 - Ver Jogo");
            System.out.println("2 - Novo Jogo");
        }
        else{
            System.out.println("\n1 - Novo Jogo");
        }
        System.out.print("Indique a Opcao: ");
        int op = sc.nextInt();

        switch (op){
            case 1:
                if(existeJ) {
                    mostrarJogos(ge, true);
                    System.out.println("Indique o número do Jogo: ");
                    int nJog = sc.nextInt();
                    verDetalhes(ge, nJog);
                }
                else novoJogo(ge);
                break;
            case 2:
                if(existeJ)
                     novoJogo(ge);
                else return;
                break;
            default: break;
        }
    }

    public static void menuGestao(){
        System.out.println("--------------Gerir Equipas-------------\n");
        System.out.println("1 - Criar Equipa");
        System.out.println("2 - Ver Equipas");
        System.out.println("3 - Remover Equipa");
        System.out.println("4 - Adicionar Jogador");
        System.out.println("5 - Transferir Jogador");
        System.out.println("6 - Remover Jogador");
        System.out.println("0 - Voltar");
        System.out.print("Indique a Opcao: ");
    }

    public static Equipa criarEquipa(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Nome da Equipa:");
        String nome = sc.nextLine();

        Equipa equipa = new Equipa(nome);

        do{
            System.out.println("----------------------");
            System.out.println("1 - Adicionar Jogador");
            System.out.println("2 - Remover Jogador");

            System.out.println("\n0 - Inicio");

            int esc = sc.nextInt();
            if(esc == 0)
                return equipa;

            switch (esc){
                case 1: equipa.addJogador(menuCriaJogador(equipa));
                break;
                case 2:
                    if(equipa.getEquipa().isEmpty())
                        break;
                    System.out.println(equipa);
                    System.out.print("Indique o número do Jogador a remover: ");
                    String num = sc.next();
                    equipa.removeJogador(num);
                break;
                default: break;
            }

        }while(true);
    }

    public static void menuCarregarFich(Gestao ge) throws IOException, ClassNotFoundException, LinhaIncorretaException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------");
        System.out.println("Carregar Ficheiro");
        System.out.println("1 - Ficheiro Objeto");
        System.out.println("2 - Ficheiro de Texto");

        System.out.println("\n0 - Terminar");
        System.out.print("Indique a opcao: ");

        switch (sc.nextInt()){
            case 1: menuCarregarFichObjeto(ge);
            break;
            case 2: menuCarregarFichTxt(ge);
            break;
            case 0: return;
            default: return;
        }
    }

    public static void menuGravarFich(Gestao ge) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------");
        System.out.println("Gravar Ficheiro");
        System.out.println("1 - Ficheiro Objeto");
        System.out.println("2 - Ficheiro de Texto");

        System.out.println("\n0 - Terminar");
        System.out.print("Indique a opcao: ");

        switch (sc.nextInt()){
            case 1: menuGravarFichObjeto(ge);
                break;
            case 2: menuGravarFichTxt(ge);
                break;
            case 0: return;
            default: return;
        }
    }

    public static void menuCarregarFichObjeto(Gestao ge) throws IOException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        List<String> ficheiros;
        String filePath = new File("").getAbsolutePath();
        File file = new File(filePath + "/src/Saves/");
        ficheiros = Arrays.stream(file.list()).filter(e -> e.endsWith(".obj")).collect(Collectors.toList());
        int pos = 1;
        for(String s : ficheiros){
            System.out.println(pos + " - " + s);
            pos++;
        }

        System.out.println("\n0 - Voltar");

        System.out.print("Indique o Ficheiro: ");
        int num = sc.nextInt();
        if(num == 0) return;

        ge.carregarFicheiroObjeto(ficheiros.get(num - 1));
    }

    public static void menuGravarFichObjeto(Gestao ge) throws IOException {
        Scanner sc = new Scanner(System.in);


        System.out.println("0 - Voltar");
        System.out.print("Indique o Ficheiro: ");
        String nome = sc.nextLine();
        if(nome.equals("0")) return;
        ge.guardarFicheiroObjeto(nome);
    }

    public static void menuCarregarFichTxt(Gestao ge) throws LinhaIncorretaException {
        Scanner sc = new Scanner(System.in);
        List<String> ficheiros;
        String filePath = new File("").getAbsolutePath();
        File file = new File(filePath + "/src/Saves/");
        ficheiros = Arrays.stream(file.list()).filter(e -> e.endsWith(".txt")).collect(Collectors.toList());
        int pos = 1;
        for(String s : ficheiros){
            System.out.println(pos + " - " + s);
            pos++;
        }

        System.out.println("\n0 - Voltar");

        System.out.print("Indique o Ficheiro: ");
        int num = sc.nextInt();

        if(num == 0) return;

        try {
            if (!(num > ficheiros.size())) {
                Parser.parse(ficheiros.get(num - 1), ge);
            } else throw new FicheiroNaoExisteException("Ficheiro Não Existente!");
        }catch (FicheiroNaoExisteException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuGravarFichTxt(Gestao ge) throws Exception {
        Scanner sc = new Scanner(System.in);


        System.out.println("0 - Voltar");
        System.out.print("Indique o Ficheiro: ");
        String nome = sc.nextLine();
        if(nome.equals("0")) return;
        ge.guardarFicheiroTxt(nome);
    }

    public static Futeboleiro menuCriaJogador(Equipa e) {

        Scanner sc = new Scanner(System.in);
        boolean existe = true;
        String nome = sc.nextLine();
        String num;
        do{
            System.out.print("Indique o numero: ");
            num = sc.nextLine();
            if (e.getEquipa().containsKey(num)) {
                System.out.println("Numero Indisponivel!");
            } else existe = false;
        }while(existe);

        System.out.println("Indique a posicao: ");
        System.out.println("GR - Guarda-Redes");
        System.out.println("DEF - Defesa");
        System.out.println("LAT - Lateral");
        System.out.println("MED - Medio");
        System.out.println("AVA - Avancado");
        System.out.print("Posicao: ");
        String pos = sc.nextLine();

        System.out.println("Indique o Histórico de Clubes: " + "(terminar com 0)");
        ArrayList<String> clubes = new ArrayList<>();
        String clube;
        do{
            clube = sc.nextLine();
            if(!clube.equals("0"))
                clubes.add(clube);
        }while(!(clube.equals("0")));
        System.out.println(clubes);

        System.out.println("Indique a Velocidade: ");
        int vel = sc.nextInt();
        System.out.print("Indique a Resistencia: ");
        int res = sc.nextInt();
        System.out.print("Indique a Destreza: ");
        int des = sc.nextInt();
        System.out.print("Indique a Impulsao: ");
        int imp = sc.nextInt();
        System.out.print("Indique o Jogo de Cabeça: ");
        int jc = sc.nextInt();
        System.out.print("Indique o Remate: ");
        int rem = sc.nextInt();
        System.out.print("Indique a Capacidade de Passe: ");
        int cp = sc.nextInt();

        switch (pos){
            case "GR":
                System.out.print("Indique a Elasticidade: ");
                int ela = sc.nextInt();
                System.out.print("Indique o Mergulho: ");
                int mer = sc.nextInt();
                return new GuardaRedes(num, nome, vel, res, des, imp, jc, rem, cp, clubes, ela, mer);
            case "DEF":
                System.out.print("Indique o Desarme: ");
                int desa = sc.nextInt();
                System.out.print("Indique a Força: ");
                int forc = sc.nextInt();
                return new Defesa(num, nome, vel, res, des, imp, jc, rem, cp, clubes, desa, forc);
            case "LAT":
                System.out.print("Indique o Cruzamento: ");
                int cruz = sc.nextInt();
                System.out.print("Indique a Aceleracao: ");
                int ace = sc.nextInt();
                return new Lateral(num, nome, vel, res, des, imp, jc, rem, cp, clubes, ace, cruz);
            case "MED":
                System.out.print("Indique a Recuperacao de Bola: ");
                int rb = sc.nextInt();
                System.out.print("Indique a Visao de Jogo: ");
                int vj = sc.nextInt();
                return new Medio(num, nome, vel, res, des, imp, jc, rem, cp, clubes, rb, vj);
            case "AVA":
                System.out.print("Indique a Finalizacao: ");
                int fin = sc.nextInt();
                System.out.print("Indique o Drible: ");
                int drib = sc.nextInt();
                return new Avancado(num, nome, vel, res, des, imp, jc, rem, cp, clubes, fin, drib);
            default: System.out.println("Posição Errada!");
        }
        return null;
    }

    public static String menuVerEquipas(Gestao ge, boolean verJ) throws EquipaNaoExisteException, JogadorNaoExisteException{

        Scanner sc = new Scanner(System.in);

        if(ge.getEquipas().isEmpty()) {
            System.out.println("Nenhuma Equipa Encontrada!");
            return null;
        }
        else System.out.print(ge.toString());
        System.out.println("\n0 - Voltar");
        System.out.print("Introduzir nome de equipa:" );
        String equip = sc.nextLine();

        if(equip.equals("0")) return "";


        try{
            if(ge.getEquipas().containsKey(equip)) {
                Equipa e = ge.getEquipas().get(equip);
                if(verJ){
                    System.out.println(e.toString());
                    System.out.println("\n0 - Voltar");
                    System.out.print("Indique o Numero do Jogador para Observar: ");
                    String num = sc.nextLine();
                    if(e.getEquipa().containsKey(num)){
                        System.out.println(e.getEquipa().get(num).toString());
                    }
                    else throw new JogadorNaoExisteException();
                }

                return equip;
            }
            else throw new EquipaNaoExisteException("Equipa Nao Existente");

        }catch (EquipaNaoExisteException | JogadorNaoExisteException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void removerEquipa(Gestao ge) throws JogadorNaoExisteException, EquipaNaoExisteException {

        String equipa = menuVerEquipas(ge, false);
        ge.removeEquipa(equipa);
    }

    public static void menuAdicionarJogador(Gestao ge) throws EquipaNaoExisteException, JogadorNaoExisteException {

        Scanner sc = new Scanner(System.in);

        String op = menuVerEquipas(ge, false);
        if(op != null) {
            Equipa e = ge.getEquipas().get(op);
            e.addJogador(menuCriaJogador(e));
        }

    }

    public static void menuTransferirJogador(Gestao ge) throws NumeroIndisponivelException, EquipaNaoExisteException, JogadorNaoExisteException {

        Scanner sc = new Scanner(System.in);
        String ant = menuVerEquipas(ge, false);
        System.out.println(ge.getEquipas().get(ant).toString());
        System.out.print("Indique o Número do Jogador a transferir: ");
        String numA = sc.nextLine();
        String numN = numA;
        System.out.print("Indique para qual Equipa Transferir: ");
        String nova = menuVerEquipas(ge, false);
        while(!ge.transfereJogador(numA, numN, ant, nova)){
            System.out.print("Indique um novo número:");
            numN = sc.nextLine();
        }
    }

    public static void menuRemoveJogador(Gestao ge) {
        try{
            Scanner sc = new Scanner(System.in);
            String eq = menuVerEquipas(ge, false);

            System.out.println(ge.getEquipas().get(eq).toString());
            System.out.print("Indique o Número do Jogador a Remover: ");
            String num = sc.nextLine();
            if (ge.getEquipas().get(eq).getEquipa().containsKey(num)){
                ge.getEquipas().get(eq).removeJogador(num);
                System.out.println("Jogador Removido com Sucesso!");
            }
            else throw new JogadorNaoExisteException("Jogador Nao Existe");

        }
        catch(JogadorNaoExisteException | EquipaNaoExisteException s){
            System.out.println(s.getMessage());
        }
    }

    public static boolean mostrarJogos(Gestao ge, boolean sim){
        int i = 1;
        System.out.println("-------JOGOS-------");
        if(ge.getJogos().size() > 0) {
            for (Jogo j : ge.getJogos()) {
                if(sim)
                    System.out.print(i + " - ");
                System.out.println(j.getEquipaCasa() + " " + j.getGolosCasa() + " - " + j.getGolosFora() + " " + j.getEquipaFora());
                i++;
            }
        }
        else {
            System.out.println("Não Existe Nenhum Jogo");
            return false;
        }
        return true;
    }

    public static void verDetalhes(Gestao ge, int nJog){
        System.out.println(ge.getJogos().get(nJog - 1).toString());
    }

    public static void novoJogo(Gestao ge) throws JogadorNaoExisteException, EquipaNaoExisteException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        String eqCasa =  "";
        String eqFora = "";
        int j = 0;
        ArrayList<String> jogTitCasa = new ArrayList<>();
        ArrayList<String> jogTitFora = new ArrayList<>();
        LocalDateTime data = LocalDateTime.now();
        HashMap<String, String> subCasa = new HashMap<>();
        HashMap<String, String> subFora = new HashMap<>();
        Equipa equipaCasa = new Equipa();
        Equipa equipaFora = new Equipa();

        /*
        System.out.println("Indique uma Data(DD-MM-AAAA): ");
        System.out.println("Dia: ");
        System.out.println("Mes: ");
        System.out.println("Ano: ");

        int dia = sc.nextInt();
        int mes = sc.nextInt();
        int ano = sc.nextInt();
        LocalDate date = new LocalDate.parse("12/02/2003", "DD/MM/YYYY");

         */

        do {
            //Escolher Equipa Visitada
            if(j == 0){
                eqCasa = menuVerEquipas(ge, false);
                if(eqCasa == null) return;
            }
            //Escolher Equipa Visitante
            if(j == 1){
                eqFora = menuVerEquipas(ge, false);
                if(eqFora == null) return;
            }

            //Equipa não existe,
            if(eqCasa.equals(eqFora)){ j--; continue;}
            if(j == 0) {
                equipaCasa = ge.getEquipas().get(eqCasa);
                System.out.println(equipaCasa.toString());
            }
            if(j == 1){
                equipaFora = ge.getEquipas().get(eqFora);
                System.out.println(equipaFora.toString());
            }


            //Tática
            System.out.println("Escolha uma Tática: ");
            System.out.println("1 - 1-4-4-2");
            System.out.println("2 - 1-4-3-3");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            //11 Titulares
            System.out.print("Indique o número de 11 Jogadores Titulares: ");
            for (int i = 0; i < 11; i++) {
                String num = sc.nextLine();

                //Titulares Visitados
                if(j == 0) {
                    if(!defineTitular(equipaCasa, num, jogTitCasa, "Casa")){
                        i--;
                        continue;
                    }
                }

                //Titulares Visitantes
                if(j == 1){
                    if(!defineTitular(equipaFora, num, jogTitFora, "Fora")){
                        i--;
                        continue;
                    }
                }
                System.out.println("Jogador Adicionado com Sucedido!");
            }


            //Substituicoes
            for(int i = 0; i < 3; i++) {
                System.out.println("Indique as Substituições (Sai->Entra): ");
                System.out.print("Sai: ");
                String sai = sc.nextLine();

                //Substituicoes Visitados
                if(j == 0){
                    if(!defineSubstituto(jogTitCasa, sai, subCasa)){
                        i--;
                        continue;
                    }
                }

                //Substituicoes Visitantes
                if(j == 1){
                    if(!defineSubstituto(jogTitFora, sai, subFora)){
                        i--;
                        continue;
                    }
                }
            }
            System.out.println("Equipa Registada!");
            j++;
        } while(j < 2);

        ge.novoJogo(eqCasa, eqFora, 0, 0, LocalDate.now(), jogTitCasa, jogTitFora, subCasa, subFora);

    }

    public static boolean defineTitular(Equipa eq, String numJ, ArrayList<String> jogTit, String loc){
        System.out.println("Titulares " + loc + ": " + jogTit);
        if(jogTit.contains(numJ) || !eq.getEquipa().containsKey(numJ)) {
            System.out.println(numJ);
            System.out.println("Numero Ja Utilizado ou não Existente");
            return false;
        }
        else {
            jogTit.add(numJ);
            return true;
        }
    }

    public static boolean defineSubstituto(ArrayList<String> jogTit, String sai, Map<String, String> sub){
        Scanner sc = new Scanner(System.in);
        if (!jogTit.contains(sai) || sub.containsKey(sai)) {
            System.out.println("Jogador já não está em campo!");
            return false;
        }
        System.out.print("Entra: ");
        String entra = sc.nextLine();
        if(jogTit.contains(entra) || sai.equals(entra) || sub.containsValue(entra)) {
            System.out.println("Jogador já está em Campo!");
            return false;
        }
        sub.put(sai, entra);
        return true;
    }
}