/**
 * Implementation of Vertex class 
 * @author ameliado
 */
public class Vertex
{
    String label = "";
    Double distance = 0.0;
    Vertex parent;
    int index; 
    boolean visited;

    public Vertex(String theLabel, int theIndex)
    {
        this.label = theLabel;
        this.index = theIndex; 
    }

    public String toString()
    {
    	if (this.parent == null) {
    		return this.label + ":" + + this.distance + ":*";
    	}
        return this.label + ":" + + this.distance + ":" + parent.label;
    }

    public void reset()
    {
        this.visited = false;
        this.parent = null;
        this.distance = Double.MAX_VALUE;
    }
}
