package TP1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TP1 {
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
        System.out.println(nombre_de_sommets);

        int index = 0;
        Graph<Integer> graph = new Graph<Integer>(nombre_de_sommets);
        while(sc.hasNextLine()){
            int litteral1 = sc.nextInt();
            System.out.println(litteral1);
            int litteral2 = sc.nextInt();
            sc.nextInt();
            System.out.println(litteral2);
            graph.addArc(litteralToVertex(-litteral1), litteralToVertex(litteral2), index++);
            graph.addArc(litteralToVertex(-litteral2), litteralToVertex(litteral1), index++);
        }
        System.out.println(graph.toString());

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
