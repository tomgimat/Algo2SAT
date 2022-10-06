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

    public HashMap<LinkedList<Edge>, Integer> parcours_en_profondeur(){
        HashMap<LinkedList<Edge>, Integer> datesFin = new HashMap<>();
        HashMap<LinkedList<Edge>, Boolean> marked = new HashMap<>();


        incidency.forEach(i->{
            marked.put(i, false);
        });
        int date = 0;
        for(LinkedList<Edge> sommet : incidency){
            if(!marked.get(sommet)){
                date = explore(sommet, date+1, marked, datesFin);
                datesFin.put(sommet, date);
            }
        }
        return datesFin;
    }
    public int explore(LinkedList<Edge> sommet, int date, HashMap<LinkedList<Edge>, Boolean> marked, HashMap<LinkedList<Edge>, Integer> datesDeFin){

        //on marque le sommet que l'on visite
        marked.put(sommet, true);
        System.out.println("Date actuelle : " + date + " \n-----");
        //pour chaque sommet voisin
        for(Edge s : sommet){
            System.out.println("sommet actuel : " + s.source + "\n");
            //s'il n'a pas déjà été visité on appelle explore récursivement dessus en augmentant la date actuelle de 1
            if(!marked.get(incidency.get(s.destination))){
//                System.out.println("Sommet suivant : " + incidency.get(s.destination) + incidency.get(s.destination).size());
                date = explore(incidency.get(s.destination), date+1, marked, datesDeFin);
            } else {
                System.out.println("Déjà visité");
            }
        }
        //On augmente et retourne la date actuelle quand il n'y a plus de sommet voisin à visiter
        date++;
        datesDeFin.put(sommet, date);
        return date;
    }
}