/**
 * Implementation of Graph 
 * @author ameliado
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph
{
	private Map<String, Vertex> vertices;
	private Map<String, List<Edge>> edges;

	public Graph() {
		vertices = new HashMap<>();
		edges = new HashMap<>();
	}

	/**
	 * Reads a graph from the file with the given filename
	 * @param filename - file to be read from
	 */
	public Graph(String filename) throws FileNotFoundException
	{ 
		vertices = new HashMap<>();
		edges = new HashMap<>();
		
		File file = new File(filename);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
        	String line = scanner.nextLine().trim();
        	String[] data = line.split(" ");
        	
        	String sourceLabel = data[0];
        	String targetLabel = data[1];
        	Double weight = Double.parseDouble(data[2]);
        	addEdge(sourceLabel, targetLabel, weight);
        }
        
        scanner.close();
	}

	/**
	 * Adds an edge with the given weight from the vertex with the given source label to the given target
	 * @param sourceLabel - the label of the source vertex
	 * @param targetLabel - the label of the target vertex
	 * @param weight      - weight of the source vertex to be added to
	 */
	public void addEdge(String sourceLabel, String targetLabel, double weight)
	{
		Vertex source = getVertex(sourceLabel);
		Vertex target = getVertex(targetLabel);

		List<Edge> sourceEdges = edges.get(sourceLabel);
		
		Edge edge = new Edge(source, target, weight);
		sourceEdges.add( edge );
		edges.put(sourceLabel, sourceEdges);
	}

	/**
	 * Returns a list of the edges that have the given vertex as their source
	 * @param source - the source where the edges are from
	 * @return a list of the edges that have the given vertex as their source
	 */
	public List<Edge> getAdjacent(Vertex source) 
	{
		return Collections.unmodifiableList(edges.get(source.label));
	}

	/**
	 * Returns a set of the vertices in the graph
	 * @return a set of the vertices in the graph
	 */
	public Collection<Vertex> getVertices() 
	{
		return Collections.unmodifiableCollection( vertices.values() );
	}

	/**
	 * Returns string representation of the summary of the graph
	 * @return string representation of the summary of the graph
	 */
	public void printMST()
	{
		int totalWeight = 0;
		List<Edge> edgeList = new LinkedList<>();
		
	    for (String label : edges.keySet() ) {
	        List<Edge> adjEdges = edges.get(label);
	        for (Edge edge : adjEdges) {
	        	edgeList.add(edge);
	            totalWeight += edge.weight;
	        }
	    }
	    
	    edgeList.sort( new EdgeComparator() );
	    for (Edge edge : edgeList) {
	    	System.out.println(edge);
	    }
	    System.out.println("Total Weight: " + totalWeight);
	}

	/**
	 * Returns a list of all edges of the graph
	 * @return a list of all edges of the graph
	 */
	public List<Edge> getEdges()
	{
		List<Edge> list = new LinkedList<>();
		for (String label : edges.keySet()) {
			List<Edge> adjEdges = edges.get(label);
			list.addAll(adjEdges);
		}
		return list;
	}

	/**
	 * Returns a Vertex object for the given label and its index
	 * @param label - the given label to get vertex
	 * @return a vertex object for the given label and its index
	 */
	public Vertex getVertex(String label)
	{
		if (!vertices.containsKey(label)) {
			int idx = vertices.size();
			
			Vertex vertex = new Vertex(label, idx);
			vertices.put(label, vertex);
			
			List<Edge> sourceEdges = new LinkedList<Edge>();
			edges.put(label, sourceEdges);
		}
		
		return vertices.get(label);
	}

	/**
	 * Returns an array of labels of the vertices in the graph
	 * @return an array of labels of the vertices in the graph
	 */
	public String[] getLabels()
	{
		String[] labels = new String[vertices.size()];
		for (Vertex v : this.getVertices()) {
			int idx = v.index;
			labels[idx] = v.label;
		}
		return labels;
	}

	/**
	 * Returns the adjacency matrix of the graph
	 * @return the adjacency matrix of the graph
	 */
	public double[][] getMatrix()
	{
		int n = vertices.size();
	    double[][] matrix = new double[n][n];
	    for (int i = 0; i < n; i++) {
	        Arrays.fill(matrix[i], Double.POSITIVE_INFINITY);
	    }
	    for (int i = 0; i < n; i++) {
	        matrix[i][i] = 0;
	    }

	    for (List<Edge> edgeList : edges.values()) {
	        for (Edge edge : edgeList) {
	            int sourceIndex = edge.source.index;
	            int targetIndex = edge.target.index;
	            matrix[sourceIndex][targetIndex] = edge.weight;
	        }
	    }
	    return matrix;
	}

}