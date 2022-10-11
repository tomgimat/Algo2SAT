package TP1;

import java.util.*;

public class Graph_me<Label> {

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

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;

    public ArrayList<LinkedList<Edge>> getIncidency() {
        return incidency;
    }

    public HashMap<LinkedList<Edge>, Integer[]> getDates() {
        return dates;
    }

    public LinkedList<LinkedList<Edge>> getMarked() {
        return marked;
    }

    private LinkedHashMap<LinkedList<Edge>, Integer[]> dates;


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

    public void parcours_en_profondeur(){

        for(LinkedList<Edge> sommet : incidency){
            if(!marked.contains(sommet)){
                System.out.println("On repart du sommet de depart");
                explore(sommet);
            }
        }
    }

    public void explore(LinkedList<Edge> sommet){
        int date_debut = date;
        System.out.println("exploring ");
        marked.add(sommet);
        for(Edge s : sommet){
            System.out.println("Nous empruntons l'arc " + s.label + " a la date " + date);

//            System.out.println("exploring edge");
            //s'il n'a pas déjà été visité on appelle explore récursivement dessus en augmentant la date actuelle de 1
            if(!marked.contains(incidency.get(s.destination))){
                date += 1;
//                System.out.println("Exploration du sommet '" + s.destination + "' a la date '" + date + "'");
                explore(incidency.get(s.destination));
            } else {
//                System.out.println("Deja visite");
            }
        }
        System.out.println("Fin de l'exploration pour ce sommet");
        //On augmente et retourne la date actuelle quand il n'y a plus de sommet voisin à visiter
        date += 1;
        dates.put(sommet, new Integer[]{date_debut, date});

    }
}