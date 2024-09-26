import java.io.FileNotFoundException;
import java.io.IOException;

public class GraphExplorer {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException  
	{
		String algo = args[0];
		Graph graph = new Graph(args[1]);
		
		if (algo.equals("-bfs")) {
			String startV = args[2];
			GraphAlgos.bfs(graph, startV);
		}
		else if (algo.equals("-dfs")) {
			String startV = args[2];
			GraphAlgos.dfs(graph, startV);
		}
		else if (algo.equals("-dijkstra")) {
			String startV = args[2]; 
			GraphAlgos.dijkstra(graph, startV);
		}
		else if (algo.equals("-prim")) {
			String startV = args[2];
			GraphAlgos.prim(graph, startV);
		}
		else if (algo.equals("-fw")) {
			GraphAlgos.floydWarshall(graph);
		}
		else if (algo.equals("-kruskal")) {
			GraphAlgos.kruskal(graph);
		}
	}

}
