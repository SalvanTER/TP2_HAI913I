package main;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import graph.Graph;
import graph.Link;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodInvocation;

import utils.MethodOfClass;
import utils.Parser;

public class CouplingGraph {

	private Parser parser;
	private int nbAllRelations;
	private Graph graph;
	public CouplingGraph(Parser parser)
	{
		graph = new Graph();
		this.parser = parser;
		initNbAllRelations();
	}
	public int getNbAllRelations()
	{
		return nbAllRelations;
	}
	public int initNbAllRelations()
	{
		
		for(int i = 0; i < parser.getClassesDeclaration().size()-1; i++)
		{
			for(int j = i+1; j < parser.getClassesDeclaration().size(); j++)
			{
				nbAllRelations += getNbRelations(parser.getClassesDeclaration().get(i), parser.getClassesDeclaration().get(j));
			}
		}
		return nbAllRelations;
	}
	
	//Calcul du couplage entre deux classes
	public float couplage(TypeDeclaration c1, TypeDeclaration c2)
	{
		return (float)getNbRelations(c1, c2)/(float)nbAllRelations;
	}
	
	//Compte le nombre de relation entre deux classes
	public int getNbRelations(TypeDeclaration c1, TypeDeclaration c2)
	{	
		return getNumberOfMethodsCalledFromClass(c1, c2) + getNumberOfMethodsCalledFromClass(c2, c1);
	}
	private int getNumberOfMethodsCalledFromClass(TypeDeclaration c1, TypeDeclaration c2)
	{
		int res = 0;
		List<MethodOfClass> mcs1 = parser.getMethodsOfClass(c1);
		for(MethodOfClass mc : mcs1)
		{
			ArrayList<MethodInvocation> mis = parser.getInvocationTree().get(mc);
			for(MethodInvocation mi : mis)
			{
				if(c2.getName().toString().equals(getObjectType(mi)))
				{
					res++;
				}
			}
		}
		return res;
	}
	private String getObjectType(MethodInvocation mCalled) {
			
		if(mCalled.getExpression()!=null) {
				if(mCalled.getExpression().resolveTypeBinding()!=null) {
					return mCalled.getExpression().resolveTypeBinding().getName();	
				}
			}
		else {
			if(mCalled.resolveMethodBinding()!=null)
			return mCalled.resolveMethodBinding().getDeclaringClass().getName();	
		}
		return "";
	}
	public void generateCouplingGraph()
	{
		for(int i = 0; i < parser.getClassesDeclaration().size()-1; i++)
		{
			for(int j = i+1; j < parser.getClassesDeclaration().size(); j++)
			{
				TypeDeclaration g1 = parser.getClassesDeclaration().get(i);
				TypeDeclaration g2 = parser.getClassesDeclaration().get(j);
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
	public ArrayList<TypeDeclaration> getNodes()
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
