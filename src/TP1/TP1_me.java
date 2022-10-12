package TP1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TP1_me {
    public static ArrayList<ArrayList<Integer>> clauses;
    public static int nombre_de_sommets;
    public static void main(String[] args) throws IOException {

        try {
            read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void read()
            throws Exception {
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
        for(List<LinkedList<Graph_me<Integer>.Edge>> cfc : graph_transposed.parcours_en_profondeur(graph.getDates())){
            for(LinkedList<Graph_me<Integer>.Edge> sommet : cfc){
                System.out.print("SOMMET------");
            }
            System.out.println("CFC");
        }

//        System.out.println("[---- DATES ----]");
//        for (LinkedList<Graph_me<Integer>.Edge> name : graph.getDates().keySet()) {
//            String value = Integer.toString(graph.getDates().get(name));
//            System.out.println(value);
//        }
//        for(String line: Files.readAllLines(path)){
//            if (line.startsWith("p")) {
//                nombre_de_variables = (int) line.charAt(6);
//                nombre_de_clauses = (int) line.charAt(8);
//                clauses = new ArrayList<>(nombre_de_clauses);
//            } else if(!line.startsWith("c") && line.endsWith("0")) {
//                String[] line_splitted = line.substring(0, line.length()-1).split(" ");
//                clauses.add(new ArrayList<Integer>(nombre_de_variables));
//                for(String str : line_splitted){
//                    clauses.get(clauses.size()-1).add(Integer.parseInt(str));
//                }
//
//            } else {
//                throw new Exception("ddddd");
//            }
//        }
    }

    public static int litteralToVertex(int litteral){
        return litteral>0 ? (2*litteral-2) : (-2*litteral-1);
    }
}
