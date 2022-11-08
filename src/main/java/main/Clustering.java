package main;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import dendrogram.Cluster;
import graph.Graph;
import processing.core.PApplet;


public class Clustering extends PApplet{
	static Graph graph;
	static ArrayList<Cluster> clusters; 
	static Cluster dendro;
	static int posDendro = 0;
	public Clustering()
	{
		super();
	}
	public Clustering(Graph graph)
	{
		Clustering.graph = graph;
		Clustering.clusters = new ArrayList<Cluster>(); 
	}
	public void clusteringHierarchique()
	{
		initClusters();
		while(clusters.size() > 1)
		{
			Cluster[] clustersProche = clustersProche();
			Cluster c3 = new Cluster(clustersProche);
			clusters.remove(clustersProche[0]);
			clusters.remove(clustersProche[1]);
			clusters.add(c3);
 		}
		dendro = clusters.get(0);
	}
	public Cluster getDendrogram()
	{
		return dendro;
	}
	public void initClusters()
	{
		for(TypeDeclaration c : graph.getNodes())
		{
			clusters.add(new Cluster(c));
		}
	}
	public Cluster[] clustersProche()
	{
		Cluster[] clustersProche = new Cluster[2]; 
		float minValue = 0;
		for(int i = 0; i < clusters.size()-1; i++)
		{
			for(int j = i+1;  j < clusters.size(); j++)
			{
				float currentDist = distanceCluster(clusters.get(i), clusters.get(j));
				if(currentDist >= minValue)
				{
					minValue = currentDist;
					clustersProche[0] = clusters.get(i);
					clustersProche[1] = clusters.get(j);
				}
			}
		}
		return clustersProche;
	}
	public float distanceCluster(Cluster c1, Cluster c2)
	{
		float res = 0;
		int i = 0;
		for(TypeDeclaration t1 : c1.getNodes())
		{
			for(TypeDeclaration t2 : c2.getNodes())
			{
				res += graph.getDistanceBetweenNodes(t1, t2);
				i++;
			}
		}
		return ((float)res)/i;
	}
	public void printResult()
	{
		System.out.println(dendro.toString());
	}
	public void plotDendrogram()
	{
		PApplet.main(Clustering.class.getName());
	}
	@Override
    public void settings(){
        size(1280, 700, FX2D);
    }
	@Override
    public void setup(){

		background(150);
		drawDendro(dendro, 0, 0, 0, dendro.getNodes().size());
			
    }
	public void drawDendro(Cluster c, int profondeur, float x, float y, int nbNodes)
	{
		profondeur++;
    	fill(50, 50,200);
    	int textSize = 10;
    	textSize(textSize);
    	float posX1 = 50 + width/nbNodes * posDendro;
    	float posY1 = height - 30;
    	float posY2 = 10 + (height/profondeur) * (profondeur - 1);
    	float posX2 = 0;
		if(c.getLeft().getNodes().size() == 1)
		{
			posX1 = 50 + width/nbNodes * posDendro;
			posY1 = height - 30;
			posY2 = 10 + (height/profondeur) * (profondeur - 1);
			String text = c.getLeft().getNodes().get(0).getName().toString().replace(' ', '\n');
			line(posX1, posY1, posX1, posY2);
			text(text, posX1 - text.length(), height - 20);
			posDendro++;
		}
		else
		{
			drawDendro(c.getLeft(), profondeur, x, y, nbNodes);
		}
		if(c.getRight().getNodes().size() == 1)
		{
			posX2 = 50 + width/nbNodes * posDendro;
			String text = c.getRight().getNodes().get(0).getName().toString().replace(' ', '\n');
			line(posX2, posY1,posX2, posY2);
			text(text, posX2 - text.length(), height - 20);
			posDendro++;
		}
		else
		{
			drawDendro(c.getRight(), profondeur, x, y, nbNodes);
		}
		line(posX1, posY2, posX2, posY2);
	}
}
