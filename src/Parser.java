import Posicoes.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {


    public static void parse(String nomeFich, Gestao ge) throws LinhaIncorretaException {
        List<String> linhas = lerFicheiro("/home/mata/Code/ProjetoPOO/src/Saves/" + nomeFich);
        Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
        Map<String, Futeboleiro> jogadores = new HashMap<>(); //numero, jogador
        List<Jogo> jogos = new ArrayList<>();
        Equipa ultima = null; Futeboleiro j = null;
        String[] linhaPartida;
        try{
            for (String linha : linhas) {
                linhaPartida = linha.split(":", 2);
                switch(linhaPartida[0]){
                    case "Equipa":
                        Equipa e = Equipa.parse(linhaPartida[1]);
                        equipas.put(e.getNome(), e);
                        ultima = e;
                        break;
                    case "Guarda-Redes":
                        j = GuardaRedes.parse(linhaPartida[1]);
                        jogadores.put(j.getNumero(), j);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j); //if no team was parsed previously, file is not well-formed
                        break;
                    case "Defesa":
                        j = Defesa.parse(linhaPartida[1]);
                        jogadores.put(j.getNumero(), j);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j); //if no team was parsed previously, file is not well-formed
                        break;
                    case "Medio":
                        j = Medio.parse(linhaPartida[1]);
                        jogadores.put(j.getNumero(), j);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j); //if no team was parsed previously, file is not well-formed
                        break;
                    case "Lateral":
                        j = Lateral.parse(linhaPartida[1]);
                        jogadores.put(j.getNumero(), j);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j); //if no team was parsed previously, file is not well-formed
                        break;
                    case "Avancado":
                        j = Avancado.parse(linhaPartida[1]);
                        jogadores.put(j.getNumero(), j);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j); //if no team was parsed previously, file is not well-formed
                        break;
                    case "Jogo":
                        Jogo jo = Jogo.parse(linhaPartida[1]);
                        jogos.add(jo);
                        break;
                    default:
                        throw new LinhaIncorretaException();

                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ge.setJogos(jogos);
        ge.setEquipas(equipas);


    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        Path path = Paths.get(nomeFich);
        Charset charset = StandardCharsets.UTF_8;
        try {
            lines = Files.readAllLines(path, charset);
        }
        catch(IOException exc) {
            System.out.println(exc.getMessage());
            lines = new ArrayList<>();
        }
        return lines;
    }
}