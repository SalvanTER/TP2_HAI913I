package hai913i.main.java.graph;

public class Link {
	private Point g1;
	private Point g2;
	private float weight;
	public Link(Point g1, Point g2, float weight)
	{
		this.g1 = g1;
		this.g2 = g2;
		this.weight = weight;
	}
	public Point getG1()
	{
		return g1;
	}
	public void setG1(Point g1)
	{
		this.g1 = g1;
	}
	public Point getG2()
	{
		return g2;
	}
	public void setG2(Point g2)
	{
		this.g2 = g2;
	}
	public float getWeight()
	{
		return weight;
	}
	public void setWeight(float weight)
	{
		this.weight = weight;
	}
}
