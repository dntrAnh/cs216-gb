/**
 * Implementation of Graph Algorithms Test 
 * @author ameliado
 */
import org.junit.Test;
import java.io.FileNotFoundException;

public class GraphAlgosTest
{
    // @Test
    public void test_bfs() throws FileNotFoundException {
        Graph graph = new Graph("undirected.txt");

        String[] labelsList = { "a", "b", "c", "d", "e", "f" };

        for ( int i = 0; i < labelsList.length; i++ ) {
        	System.out.println();
            System.out.println("BFS Source " + labelsList[i]);
            GraphAlgos.bfs(graph, labelsList[i]);
 
        }
    }

    // @Test
    public void test_dijkstra() throws FileNotFoundException
    {
        Graph graph = new Graph("directed.txt");

        String[] labelsList = { "a", "b", "c", "d", "e", "f" };

        for ( int i = 0; i < labelsList.length; i++ ) {
        	System.out.println();
            System.out.println("Dijkstra Source " + labelsList[i]);
            GraphAlgos.dijkstra(graph, labelsList[i]);
        }
    }

    // @Test
    public void test_dfs() throws FileNotFoundException
    {
        Graph graph = new Graph("directed.txt");

        String[] labelsList = { "a", "b", "c", "d", "e", "f" };

        for ( int i = 0; i < labelsList.length; i++ ) {
        	System.out.println();
            System.out.println("DFS Source " + labelsList[i]);
            GraphAlgos.dfs(graph, labelsList[i]);
        }
    }
    
    @Test
    public void test_prim()
    {
        Graph graph = null;
		try {
			graph = new Graph("undirected.txt");
		} 
		catch (FileNotFoundException e) {}

        String[] labelsList = { "a", "b", "c", "d", "e", "f" };

        for ( int i = 0; i < labelsList.length; i++ ) {
        	System.out.println();
            System.out.println("Prim Source " + labelsList[i]);
            GraphAlgos.prim(graph, labelsList[i]);
        }
    }
    
    @Test
    public void test_kruskal()
    {
        Graph graph = new Graph();
		try {
			graph = new Graph("undirected.txt");
		} 
		catch (FileNotFoundException e) {}

        System.out.println("Kruskal:");
        GraphAlgos.kruskal( graph );                         
    }
    
    @Test
    public void test_floydWarshall()
    {
        Graph graph = null;
		try {
			graph = new Graph("directed.txt");
		} 
		catch (FileNotFoundException e) {}

        System.out.println("Floyd-Warshall:");
        GraphAlgos.floydWarshall( graph );
    }

}
