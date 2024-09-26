/**
 * Implementation of Edge 
 * @author ameliado
 */
public class Edge
{
    Vertex source;
    Vertex target;
    double weight;

    public Edge(Vertex theSource, Vertex theTarget, double theWeight)
    {
        this.source = theSource;
        this.target = theTarget;
        this.weight = theWeight;
    }

    public Vertex getSource()
    {
        return this.source;
    }

    public Vertex getTarget()
    {
        return this.target;
    }

    public double getWeight()
    {
        return this.weight;
    }
    
    public String toString()
    {
    	return source.label + "--" + weight + "--" + target.label;
    }
}
