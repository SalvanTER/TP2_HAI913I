package hai913i.main.java.main;

import java.util.ArrayList;

import hai913i.main.java.dendrogram.Cluster;
import hai913i.main.java.graph.Graph;
import hai913i.main.java.graph.Point;
import processing.core.PApplet;


public class Clustering{
	static Graph graph;
	static ArrayList<Cluster> clusters; 
	static Cluster dendro;
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
		for(Point c : graph.getNodes())
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
		for(Point t1 : c1.getNodes())
		{
			for(Point t2 : c2.getNodes())
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
}
