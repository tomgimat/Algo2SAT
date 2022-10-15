package TP1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TP1_me {
    public static ArrayList<ArrayList<Integer>> clauses;
    public static int nombre_de_sommets;
    public static HashMap<Integer, Integer> litterals;
    public static void main(String[] args) throws IOException {

        try {
            read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void read()
            throws Exception {

        litterals = new HashMap<>();
        Path path = Paths.get("src/TP1/formule.txt");

        Scanner sc = new Scanner(path);

        nombre_de_sommets = sc.nextInt() * 2;

        int index = 0;
        Graph_me<Integer> graph = new Graph_me<Integer>(nombre_de_sommets);
        Graph_me<Integer> graph_transposed = new Graph_me<Integer>(nombre_de_sommets);
        while(sc.hasNextLine()){
            int litteral1 = sc.nextInt();
            int litteral2 = sc.nextInt();
            sc.nextInt();


            index++;
            //System.out.println("Arc numero " + index++ + " du sommet '"+ litteralToVertex(-litteral1) + "' vers le sommet '" + litteralToVertex(litteral2) + '"');
            graph.addArc(litteralToVertex(-litteral1), litteralToVertex(litteral2), index);
            //Pour le graphe transposé, on inverse simplement l'ordre des arcs.
            graph_transposed.addArc( litteralToVertex(litteral2), litteralToVertex(-litteral1),index);

            index++;
            //System.out.println("Arc numero " + index++ + " du sommet '"+ litteralToVertex(-litteral2) + "' vers le sommet '" + litteralToVertex(litteral1) + '"');
            graph.addArc(litteralToVertex(-litteral2), litteralToVertex(litteral1), index);
            graph_transposed.addArc( litteralToVertex(litteral1), litteralToVertex(-litteral2), index);
        }
        graph.parcours_en_profondeur();

        //Graphe des composantes fortement connexes

        System.out.println(check2SAT(graph_transposed.parcours_en_profondeur_transpose(graph.getDates())));

        for(List<Integer> cfc : graph_transposed.parcours_en_profondeur_transpose(graph.getDates())){
            System.out.println("[------CFC------]");
            for(Integer sommet : cfc){
                System.out.println("SOMMET: " + sommet);
            }
            System.out.println("[--------------]");
            System.out.println();

        }

        System.out.println("[---- DATES DE FIN ----]");
        for (Integer name : graph.getDates().keySet()) {
            String value = Integer.toString(graph.getDates().get(name));
            System.out.println("Le sommet: " + name + " se termine a la date: " + value);
        }
        System.out.println("[------------------]");


    }

    public static int litteralToVertex(int litteral){
        int vertex =  litteral>0 ? (2*litteral-2) : (-2*litteral-1);
        litterals.put(vertex, litteral);
        return vertex;
    }
    public static boolean checkCFC(List<Integer> sommets){
        List<Integer> litterals_ = new ArrayList<>();
        sommets.forEach(s->{
            litterals_.add(litterals.get(s));
        });
        for(Integer litteral : litterals_){
            if(litterals_.contains(-litteral)) return false;
        }
        return true;
    }

    public static String check2SAT(List<List<Integer>> composantes) {
        for (List<Integer> c : composantes) {
            if (!checkCFC(c)) {
                return "La formule est insatisfaisable";
            }
            return "La formule est satisfiable";
        }
        return "pipi caca";
    }

}
