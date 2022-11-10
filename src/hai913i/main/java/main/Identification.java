package hai913i.main.java.main;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import hai913i.main.java.dendrogram.Cluster;
import hai913i.main.java.graph.Graph;
import hai913i.main.java.graph.Point;

public class Identification {
	private Cluster den; 
	private float cp;
	private ArrayList<Cluster> modules;
	public Identification(Cluster den) 
	{
		this.den = den;
		Scanner input = new Scanner (System.in).useLocale(Locale.US); 
		System.out.println("Enter a cp value:");
		cp = (float)input.nextDouble();  
		modules = new ArrayList<Cluster>();
	}
	public void run(Graph g)
	{
		recursivePathDendrogram(den, g);
	}
	private void recursivePathDendrogram(Cluster c, Graph g)
	{
		if(c.getNodes().size() == 1)
		{
			return;
		}
		if(c.meanNodesCouplage(g) > cp)
		{
			modules.add(c);
		}
		else
		{
			recursivePathDendrogram(c.getLeft(), g);
			recursivePathDendrogram(c.getRight(), g);
		}
	}
	public void printResult()
	{
		int i = 0;
		for(Cluster c : modules)
		{
			i++;
			System.out.println("Module " + i + " composé des classes suivantes : ");
			for(Point t : c.getNodes())
			{
				System.out.println("-" + t.getName());
			}
		}
	}
}
