package hai913i.main.java.main;

import hai913i.main.java.cli.CLIMenu;
import hai913i.main.java.cli.CLIPlotCouplingGraph;
import hai913i.main.java.cli.CLIPrintClustering;
import hai913i.main.java.cli.CLIPrintCouplingGraph;
import hai913i.main.java.cli.CLIPrintIdentification;
import hai913i.main.java.spoon.ParserS;
import hai913i.main.java.utils.AbsParser;
import hai913i.main.java.utils.Parser;

public class Main{
	private static CouplingGraph cGraph;
	private static AbsParser parser;
	private static Clustering cluster;
	private static Identification identification;
	private static CLIMenu menu = new CLIMenu();
	public static void main(String args[])
	{
		
		System.out.println("Récupération des classes avec l'AST...");
		try {
			parser = new Parser();
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}finally {
			
		}
		
		
		ParserS parserS = new ParserS();
		parserS.run();
		
		cGraph = new CouplingGraph(parserS);
		cluster = new Clustering(cGraph.getGraph());
		
		System.out.println("Génération du graphe de couplage...");
		cGraph.generateCouplingGraph();
		System.out.println("Execution de l'algorithme de clustering hi�rarchique...");
		cluster.clusteringHierarchique();
		
		//Algorithme d'identification
		identification = new Identification(cluster.getDendrogram(), (float)0.05);
		System.out.println("Execution de l'algorithme d'identification...");
		identification.run(cGraph.getGraph());
		menu.addChoice(new CLIPrintCouplingGraph("Afficher graphe de couplage dans le terminal", cGraph));
		menu.addChoice(new CLIPlotCouplingGraph("Afficher le graphe de couplage en 2D", cGraph));
		menu.addChoice(new CLIPrintClustering("Afficher le dendrogramme dans le terminal", cluster));
		menu.addChoice(new CLIPrintIdentification("Afficher les modules identifi�s dans le terminal", identification));
		menu.run();
	}
}
