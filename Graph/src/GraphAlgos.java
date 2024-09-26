/**
 * Implementation of Graph Algorithms 
 * @author ameliado
 */

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphAlgos
{
    public static void bfs(Graph graph, String sourceLabel)
    {
        for (Vertex v : graph.getVertices()) {
            v.reset();
        }
        
        Vertex source = graph.getVertex(sourceLabel);
        source.parent = null;
        source.distance = 0.0;

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(source);
        source.visited = true;

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
           
           System.out.print(v + " ");
             
            for (Edge edge : graph.getAdjacent(v)) {
            	Vertex u = edge.getTarget();
                if (!u.visited) {
                    u.visited = true;
                    u.parent = v;
                    u.distance = v.distance + 1;
                    queue.add(u);
                }
            }
        }
    }

    public static void dfs(Graph graph, String sourceLabel) {
    	for (Vertex v : graph.getVertices()) {
            v.reset();
        }
        
        Vertex source = graph.getVertex(sourceLabel);
        source.parent = null;
        source.distance = 0.0;

        dfs(graph, source);
    }


    public static void dfs(Graph graph, Vertex curr)
    {
    	curr.visited = true;
        
        for (Edge edge : graph.getAdjacent(curr)) {
            Vertex u = edge.getTarget();
            if (!u.visited) {
                u.parent = curr;
                u.distance += 1;
                dfs(graph, u);
            }
        }
    }

    public static void dijkstra(Graph graph, String sourceLabel)
    {
    	for (Vertex v : graph.getVertices()) {
    		v.visited = false; 
    		v.parent = null;
    		v.distance = Double.MAX_VALUE; 
    	}
    	Vertex source = graph.getVertex(sourceLabel);
    	source.distance = 0.0;
    	
    	PriorityQueue<Vertex> queue = new PriorityQueue<>(new VertexComparator());
    	queue.addAll(graph.getVertices());
    	    	
    	while (!queue.isEmpty()) {
    		Vertex v = queue.poll();
    		v.visited = true;
    		
    		for (Edge edge : graph.getAdjacent(v)) {
                Vertex u = edge.getTarget();
                if (!u.visited && u.distance > edge.weight) {
                	queue.remove(u);
                    u.distance = edge.getWeight();
                    u.parent = v;
                    queue.add(u);
                }
            }
            Vertex vertex = queue.poll();
            printPathRec(source, vertex);
            printPathLoop(source, vertex);
        }
    }
    
    public static void printPathLoop(Vertex startVertex, Vertex destVertex)
    {
    	String path = "";
        Double totalLength = 0.0;

        Vertex current = destVertex;

        while (current != startVertex) {
            path = current.label + " <--" + current.distance + "-- " + path;
            totalLength += current.distance;
            current = current.parent;
        }
        path = startVertex.label + " " + path;
        
        path += "(total length " + totalLength + ")";

        System.out.println(path);
    }
    
    public static void printPathRec(Vertex startVertex, Vertex destVertex)
    {
    	if (destVertex == startVertex) {
    		System.out.print(startVertex.label);
    	}
    	else {
    		printPathRec(startVertex, destVertex.parent);
    		double weight = destVertex.distance - destVertex.parent.distance;
    		System.out.println( "--" + weight + "--> " + destVertex.label );
    	}
    }
    
    public static Graph prim(Graph graph, String sourceLabel)
    {
    	for (Vertex v : graph.getVertices()) {
    		v.visited = false; 
    		v.parent = null;
    		v.distance = Double.MAX_VALUE; 
    	}
    	Vertex source = graph.getVertex(sourceLabel);
    	source.distance = 0.0;
    	
    	PriorityQueue<Vertex> queue = new PriorityQueue<>(new VertexComparator());
    	queue.addAll(graph.getVertices());
    	
    	Graph primGraph = new Graph(); 
    	
    	while (!queue.isEmpty()) {
    		Vertex v = queue.poll();
    		v.visited = true;
    		
    		if (v.parent != null) {
                primGraph.addEdge(v.parent.label, v.label, v.distance);
            }
    		
    		for (Edge edge : graph.getAdjacent(v)) {
                Vertex u = edge.getTarget();
                if (!u.visited && u.distance > edge.weight) {
                	queue.remove(u);
                    u.distance = edge.getWeight();
                    u.parent = v;
                    queue.add(u);
                }
            }
    	}
    	primGraph.printMST();
    	return primGraph;
    }
    
    public static Graph kruskal(Graph graph)
    {
		List<Edge> sortedEdges = graph.getEdges(); 
		sortedEdges.sort( new EdgeComparator() );
		
    	Graph MSTree = new Graph(); 
        DisjointSets<Vertex> disjointSets = new DisjointSets<>( graph.getVertices() );
		
    	for (Edge edge : sortedEdges) {
            Vertex source = edge.source;
            Vertex target = edge.target;
            if (!disjointSets.sameSet(source, target)) {
                MSTree.addEdge(source.label, target.label, edge.getWeight());
                disjointSets.union(source, target);
            }
        }
    	
    	MSTree.printMST();
        return MSTree;
    }
    
    public static int[][] initPredecessor(double[][] D)
    {
    	int n = D.length;
        int[][] P = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && D[i][j] < Double.POSITIVE_INFINITY) {
                    P[i][j] = i;
                } 
                else { //if (i == j) {
                    P[i][j] = -1; 
                }
            }
        }
        return P;
    	
    }
    
    public static void floydWarshall(double[][] D, int[][] P)
    {
    	int n = D.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (D[i][k] + D[k][j] < D[i][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        P[i][j] = P[k][j];
                    }
                }
            }
        }
    }
    
    public static void floydWarshall(Graph G)
    {
    	double[][] D = G.getMatrix();
        int[][] P = initPredecessor(D);
        
        floydWarshall(D, P);
        
        String[] labels = G.getLabels();
        
        printAllPaths(D, P, labels);
    }
    
    public static void printAllPaths(double[][] D, int[][] P, String[] labels)
    {
    	int n = D.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && D[i][j] < Double.POSITIVE_INFINITY) {
                    printPath(i, j, D, P, labels);
                    System.out.println();
                }
            }
        }
    }
    
    public static void printPathLoop(int i, int j, double[][] D, int[][] P, String[] labels) 
    {
        String reversePath = "";
        String forwardPath = "";
        double totalLength = 0;
        int current = j;

        while (current != i) {
            int predecessor = P[i][current];
            double length = D[predecessor][current];
            reversePath = " <--" + length + "-- " + labels[current] + reversePath;
            forwardPath = forwardPath + " --" + length + "--> " + labels[current];
            totalLength += length;
            current = predecessor;
        }

        reversePath = labels[i] + reversePath + " (total length " + totalLength + ")";
        forwardPath = labels[i] + forwardPath;

        System.out.println(reversePath);
        System.out.println(forwardPath);
    }

    
    public static void printPath(int i, int j, double[][] D, int[][] P, String[] labels) 
    {
        printPathRecursive(i, j, D, P, labels, "", 0.0);
    } 

    private static void printPathRecursive(int i, int j, double[][] D, int[][] P, String[] labels, String forwardPath, double totalLength) 
    {
        if (P[i][j] == i) {
            forwardPath += labels[i] + " --" + D[i][j] + "--> " + labels[j];
            totalLength += D[i][j];

            System.out.println(forwardPath + " (total length " + totalLength + ")");

            printReversePath(i, j, D, P, labels, totalLength);
        } 
        else {
            if (forwardPath.isEmpty()) {
                forwardPath += labels[i] + " --" + D[i][P[i][j]] + "--> ";
            } 
            else {
                forwardPath += labels[P[i][j]] + " --" + D[P[i][j]][j] + "--> ";
            }
            totalLength += D[P[i][j]][j];
            printPathRecursive(i, P[i][j], D, P, labels, forwardPath, totalLength);
        }
    }

    private static void printReversePath(int i, int j, double[][] D, int[][] P, String[] labels, double totalLength) 
    {
        String reversePath = labels[j];
        int current = j;

        while (current != i) {
            int predecessor = P[i][current];
            reversePath = labels[predecessor] + " <--" + D[predecessor][current] + "-- " + reversePath;
            current = predecessor;
        }

        System.out.println(reversePath + " (total length " + totalLength + ")");
    }


}
