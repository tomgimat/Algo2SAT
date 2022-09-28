package TP1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph<Label> {

    private class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size + 1);
        for (int i = 0; i < cardinal; i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source, dest, label));
    }

    public String toString() {
        String result = new String("");
        result+=(cardinal + "\n");
        for (int i = 0; i < cardinal; i++) {
            for (Edge e : incidency.get(i)) {
                result+=(e.source + " " + e.destination + " "
                        + e.label.toString() + "\n");
            }
        }
        return result;
    }

    public ArrayList<Integer> parcours_en_profondeur(){
        ArrayList<Integer> datesFin = new ArrayList<>();
        HashMap<LinkedList<Edge>, Boolean> marked = new HashMap<>();
        incidency.forEach(i->{
            if(!marked.get(i)){
                datesFin.add(explore(i, 0, marked));
            }
        });
        return datesFin;
    }
    public int explore(LinkedList<Edge> sommet, int date, HashMap<LinkedList<Edge>, Boolean> marked){
        marked.put(sommet, true);
        sommet.forEach(s->{
            if(!marked.get(s.destination)){
                explore(sommet,date+1, marked);
            }
        });
        return date;
    }
}