package hai913i.main.java.main;

import hai913i.main.java.graph.Graph;
import hai913i.main.java.graph.Link;
import hai913i.main.java.graph.Point;
import hai913i.main.java.utils.IMethodOfClass;
import hai913i.main.java.utils.IParser;

import java.util.ArrayList;
import java.util.List;

public class CouplingGraph {
	private IParser parser;
	private int nbAllRelations;
	private Graph graph;
	public CouplingGraph(IParser parser)
	{
		graph = new Graph();
		this.parser = parser;
		initNbAllRelations();
	}
	public int getNbAllRelations()
	{
		return nbAllRelations;
	}
	//Calcul du nombre de toute les relations entre les couples de méthodes appartenant respectivement à n'importe quelles deux classes de l'application analysés
	public int initNbAllRelations()
	{
		for(int i = 0; i < parser.getClasses().size()-1; i++)
		{
			for(int j = i+1; j < parser.getClasses().size(); j++)
			{
				nbAllRelations += getNbRelations(parser.getClasses().get(i), parser.getClasses().get(j));
			}
		}
		return nbAllRelations;
	}
	
	//Calcul du couplage entre deux classes
	public float couplage(Point c1, Point c2)
	{
		return (float)getNbRelations(c1, c2)/(float)nbAllRelations;
	}
	
	//Compte le nombre de relation entre deux classes
	public int getNbRelations(Point c1, Point c2)
	{	
		return getNumberOfMethodsCalledFromClass(c1, c2) + getNumberOfMethodsCalledFromClass(c2,c1);
	}
	private int getNumberOfMethodsCalledFromClass(Point c1, Point c2)
	{
		int res = 0;
		List<IMethodOfClass> mcs1 = parser.getMethodsOfClass(c1);
		for(IMethodOfClass mc : mcs1)
		{
			res += parser.countMethodsInClass(mc, c2);
		}
		return res;
	}
	public void generateCouplingGraph()
	{
		for(int i = 0; i < parser.getClasses().size()-1; i++)
		{
			for(int j = i+1; j < parser.getClasses().size(); j++)
			{
				Point g1 = parser.getClasses().get(i);
				Point g2 = parser.getClasses().get(j);
				float couplage = couplage(g1, g2);
				graph.addLink(new Link(g1, g2, couplage));
				graph.addNode(g1);
				graph.addNode(g2);
			}
		}
	}
	public void printGraph()
	{
		System.out.println(graph.toString());
	}
	public ArrayList<Point> getNodes()
	{
		return graph.getNodes();
	}
	public ArrayList<Link> getLinks()
	{
		return graph.getLinks();
	}
	public Graph getGraph()
	{
		return graph;
	}
	public void plotGraph()
	{
		graph.plot2d();
	}
}
