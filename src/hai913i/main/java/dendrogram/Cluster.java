package hai913i.main.java.dendrogram;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import hai913i.main.java.graph.Graph;

public class Cluster {
	private String name;
	private Cluster left;
	private Cluster right;
	private static int i = 0;
	private ArrayList<TypeDeclaration> nodes;
	public Cluster()
	{
		nodes = new ArrayList<TypeDeclaration>();
		name = "Cluster " + i;
		i++;
	}
	public Cluster(String name)
	{
		nodes = new ArrayList<TypeDeclaration>();
		this.setName(name);
	}
	public Cluster(TypeDeclaration l)
	{
		nodes = new ArrayList<TypeDeclaration>();
		nodes.add(l);
		name = "Cluster " + i;
		i++;
	}
	public ArrayList<TypeDeclaration> getNodes()
	{
		return nodes;
	}
	public Cluster(Cluster left, Cluster right)
	{
		this.setLeft(left);
		this.setRight(right);
		nodes.addAll(left.getNodes());
		nodes.addAll(right.getNodes());
		name = "Cluster " + i;
		i++;
	}
	public Cluster(Cluster[] clustersProche) {
		nodes = new ArrayList<TypeDeclaration>();
		nodes.addAll(clustersProche[0].getNodes());
		nodes.addAll(clustersProche[1].getNodes());
		setLeft(clustersProche[0]);
		setRight(clustersProche[1]);
		name = "Cluster " + i;
		i++;
	}
	public Cluster getLeft() {
		return left;
	}
	public void setLeft(Cluster left) {
		this.left = left;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cluster getRight() {
		return right;
	}
	public void setRight(Cluster right) {
		this.right = right;
	}
	@Override
	public String toString()
	{
		String res = name + ": ";
		if(left == null && right == null)
		{
			if(nodes.size() <= 2)
			{
				for(TypeDeclaration t : nodes)
				{
					res+= t.getName().toString();
				}
			}
			res += "\n";
		}
		else
		{
			res += "Left : " + left.getName() + " - " + "Right : " + right.getName() + "\n";
			res += left.toString();
			res += right.toString();
		}
		return res;
	}
	public float meanNodesCouplage(Graph g)
	{
		float res = 0;
		int m = 0;
		for(int i = 0; i < nodes.size()-1; i++)
		{
			for(int j = i+1;  j < nodes.size(); j++)
			{
				res+= g.getDistanceBetweenNodes(nodes.get(i), nodes.get(j));
				m++;
			}
		}
		return (float)(res)/m;
	}
}
