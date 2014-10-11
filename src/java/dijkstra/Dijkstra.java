/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.io.*;
import java.util.*;
 
public class Dijkstra {

   private List rotas = new ArrayList();  
   public static String pathFinal;

   private static final Graph.Edge[] GRAPH = {
      new Graph.Edge("a", "b", 10),
      new Graph.Edge("b", "d", 15),
      new Graph.Edge("a", "c", 20),
      new Graph.Edge("c", "d", 30),
      new Graph.Edge("b", "e", 50),
      new Graph.Edge("d", "e", 30),
   };
   private static final String START = "a";
   private static final String END = "d";
 
   public static String init(List<Rota2> rotas, String inicio,String fim) {
       int numeroRotas = rotas.size();
       System.out.println("teste: " +numeroRotas);
       Graph.Edge GRAPH2[] = new Graph.Edge[numeroRotas]; 


      int i =0;
     for (Rota2 rota : rotas) {
         GRAPH2[i] = new Graph.Edge(rota.origem, rota.destino, rota.distancia);
         i = i+1;
       }

      Graph g = new Graph(GRAPH2);
      g.dijkstra(inicio);
      String dist = g.printPath(fim);
       pathFinal = g.pathFinal;
       return dist;
      //g.printAllPaths();
     

   }

}
 
class Graph {
   private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

   public static int distfinal=0;
   public  static String pathFinal="";
 
   /** One edge of the graph (only used by Graph constructor) */
   public static class Edge {
      public final String v1, v2;
      public final int dist;
      public Edge(String v1, String v2, int dist) {
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
   }
 
   /** One vertex of the graph, complete with mappings to neighbouring vertices */
   public static class Vertex implements Comparable<Vertex> {
      public final String name;
      public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
      public Vertex previous = null;
      public final Map<Vertex, Integer> neighbours = new HashMap<>();
 
      public Vertex(String name) {
         this.name = name;
      }
 
      private String printPath() {
         if (this == this.previous) {
            System.out.printf("%s", this.name);
            //pathFinal = pathFinal + this.name;
            return "";
         } else if (this.previous == null) {
            System.out.printf("%s(unreached)", this.name);
           // pathFinal = pathFinal + this.name;
            return "";
         } else {
            this.previous.printPath();
            System.out.printf(" -> %s(%d)", this.name, this.dist);
            pathFinal = pathFinal + this.name;
            return this.dist +"";
         }
      }
 
      public int compareTo(Vertex other) {
         return Integer.compare(dist, other.dist);
      }
   }
 
   /** Builds a graph from a set of edges */
   public Graph(Edge[] edges) {
      graph = new HashMap<>(edges.length);
 
      //one pass to find all vertices
      for (Edge e : edges) {
         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
      }
 
      //another pass to set neighbouring vertices
      for (Edge e : edges) {
         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
      }
   }
 
   /** Runs dijkstra using a specified source vertex */ 
   public void dijkstra(String startName) {
      if (!graph.containsKey(startName)) {
         System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
         return;
      }
      final Vertex source = graph.get(startName);
      NavigableSet<Vertex> q = new TreeSet<>();
 
      // set-up vertices
      for (Vertex v : graph.values()) {
         v.previous = v == source ? source : null;
         v.dist = v == source ? 0 : Integer.MAX_VALUE;
         q.add(v);
      }
 
      dijkstra(q);
   }
 
   /** Implementation of dijkstra's algorithm using a binary heap. */
   private void dijkstra(final NavigableSet<Vertex> q) {      
      Vertex u, v;
      while (!q.isEmpty()) {
 
         u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
         if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
 
         //look at distances to each neighbour
         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
            v = a.getKey(); //the neighbour in this iteration
 
            final int alternateDist = u.dist + a.getValue();
            if (alternateDist < v.dist) { // shorter path to neighbour found
               q.remove(v);
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            } 
         }
      }
   }
 
   /** Prints a path from the source to the specified vertex */
   public String printPath(String endName) {
      if (!graph.containsKey(endName)) {
         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
         return"";
      }
 
     return graph.get(endName).printPath();
      //System.out.println();
   }
   /** Prints the path from the source to every vertex (output order is not guaranteed) */
   public void printAllPaths() {
      for (Vertex v : graph.values()) {
         v.printPath();
         System.out.println();
      }
   }
}