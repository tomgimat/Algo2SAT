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

public class TP1 {
    public static ArrayList<ArrayList<Integer>> clauses;
    public static int nombre_de_variables, nombre_de_clauses;
    public static void main(String[] args) throws IOException {
        try {
            read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(ArrayList<Integer> list : clauses){
            System.out.println(list.get(0) + " " + list.get(1) );
        }
        Graph<String> graph = new Graph<String>(nombre_de_variables*2);
    }
    public static void read()
            throws Exception {
        Path path = Paths.get("src/TP1/formule.txt");

        for(String line: Files.readAllLines(path)){
            if (line.startsWith("p")) {
                nombre_de_variables = (int) line.charAt(6);
                nombre_de_clauses = (int) line.charAt(8);
                clauses = new ArrayList<>(nombre_de_clauses);
            } else if(!line.startsWith("c") && line.endsWith("0")) {
                String[] line_splitted = line.substring(0, line.length()-1).split(" ");
                clauses.add(new ArrayList<Integer>(nombre_de_variables));
                for(String str : line_splitted){
                    clauses.get(clauses.size()-1).add(Integer.parseInt(str));
                }

            } else {
                throw new Exception("ddddd");
            }
        }
    }
}
