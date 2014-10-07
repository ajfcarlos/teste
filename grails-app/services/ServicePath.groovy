 import dijkstra.DJ;
 import dijkstra.Vertex;
 import dijkstra.Edge;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

 class ServicePath{


 	def  findPath (){
 		// mark all the vertices 
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");

        // set the edges and weight
        A.adjacencies = { new Edge(B, 10);new Edge(C,20) };
        B.adjacencies = { new Edge(D, 15);new Edge(E, 50) };
        C.adjacencies = { new Edge(D, 30) };
        D.adjacencies = { new Edge(E, 30) };
        E.adjacencies = {};




        computePaths(A); // run Dijkstra
        println("Distance to " + D + ": " + D.minDistance);
        List<Vertex> path = getShortestPathTo(D);
        return D.minDistance
 	}
	
}