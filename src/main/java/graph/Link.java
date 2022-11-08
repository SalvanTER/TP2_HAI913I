package graph;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Link {
	private TypeDeclaration g1;
	private TypeDeclaration g2;
	private float weight;
	public Link(TypeDeclaration g1, TypeDeclaration g2, float weight)
	{
		this.g1 = g1;
		this.g2 = g2;
		this.weight = weight;
	}
	public TypeDeclaration getG1()
	{
		return g1;
	}
	public void setG1(TypeDeclaration g1)
	{
		this.g1 = g1;
	}
	public TypeDeclaration getG2()
	{
		return g2;
	}
	public void setG2(TypeDeclaration g2)
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
