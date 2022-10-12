package TP1;

import java.util.*;

public class Graph_me<Label> {

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;
    private LinkedHashMap<LinkedList<Edge>, Integer> dates;
    private LinkedList<LinkedList<Edge>> marked;
    private int date;

    public Graph_me(int size) {
        cardinal = size;
        dates = new LinkedHashMap<>();
        marked = new LinkedList<>();
        date = 1;
        incidency = new ArrayList<LinkedList<Edge>>(size + 1);
        for (int i = 0; i < cardinal; i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public LinkedHashMap<LinkedList<Edge>, Integer> getDates() {
        return dates;
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source, dest, label));
    }

    public String toString() {
        String result = new String("");
        result += (cardinal + "\n");
        for (int i = 0; i < cardinal; i++) {
            for (Edge e : incidency.get(i)) {
                result += (e.source + " " + e.destination + " "
                        + e.label.toString() + "\n");
            }
        }
        return result;
    }

    public void parcours_en_profondeur() {
        for (LinkedList<Edge> sommet : incidency) {
            if (!marked.contains(sommet)) {
                explore(sommet);
                date++;
            }
        }
    }
    public List<List<LinkedList<Edge>>> parcours_en_profondeur(LinkedHashMap<LinkedList<Edge>, Integer> dates) {

        List<List<LinkedList<Edge>>> result = new ArrayList<>();
        List<LinkedList<Edge>> alKeys = new ArrayList<LinkedList<Edge>>(dates.keySet());
        Collections.reverse(alKeys);

        for(LinkedList<Edge> dt : dates.keySet()){
           for(Edge edge : dt){
               System.out.println(edge.source);
           }
        }

        System.out.println("-----------");
        for(LinkedList<Edge> dt : alKeys){
            for(Edge edge : dt){
                System.out.println(edge.source);
            }
        }

        for (LinkedList<Edge> sommet : alKeys) {
            if (!marked.contains(sommet)) {
                List<LinkedList<Edge>> cfc = new LinkedList<>();
                cfc = explore(sommet,cfc);
                date++;
                result.add(cfc);
            }
        }
        return result;
    }

    public void explore(LinkedList<Edge> sommet) {
        marked.add(sommet);
        for (Edge s : sommet) {
            if (!marked.contains(incidency.get(s.destination))) {
                date += 1;
                explore(incidency.get(s.destination));
            }
        }
        date += 1;
        dates.put(sommet, date);
    }
    public List<LinkedList<Edge>> explore(LinkedList<Edge> sommet, List<LinkedList<Edge>> cfc) {
        cfc.add(sommet);
        marked.add(sommet);
        for (Edge s : sommet) {
            if (!marked.contains(incidency.get(s.destination))) {
                date += 1;
                cfc = explore(incidency.get(s.destination), cfc);
            }
        }
        date += 1;
        dates.put(sommet, date);
        return cfc;
    }
    class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }
}