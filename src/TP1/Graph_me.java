package TP1;

import java.util.*;

public class Graph_me<Label> {

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;
    private LinkedHashMap<Integer, Integer> dates;
    private LinkedList<Integer> marked;
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

    public LinkedHashMap<Integer, Integer> getDates() {
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
        for (int i = 0; i < incidency.size(); i++) {
            if (!marked.contains(i)) {
                explore(i);
                date++;
            }
        }
    }
    public List<List<Integer>> parcours_en_profondeur_transpose(LinkedHashMap<Integer, Integer> dates) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> alKeys = new ArrayList<Integer>(dates.keySet());
        Collections.reverse(alKeys);

        for (Integer sommet : alKeys) {
            if (!marked.contains(sommet)) {
                List<Integer> cfc = new LinkedList<>();
                cfc = explore(sommet,cfc);
                date++;
                result.add(cfc);
            }
        }
        return result;
    }

    public void explore(Integer sommet) {
        marked.add(sommet);
        for (Edge s : incidency.get(sommet)) {
            if (!marked.contains(s.destination)) {

                date += 1;
                explore(s.destination);
            }
        }
        date += 1;
        dates.put(sommet, date);
    }
    public List<Integer> explore(Integer sommet, List<Integer> cfc) {
        cfc.add(sommet);
        marked.add(sommet);
        for (Edge s : incidency.get(sommet)) {
            if (!marked.contains(sommet)) {
                date += 1;
                cfc = explore(sommet, cfc);
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