package hai913i.main.java.main;

import hai913i.main.java.cli.CLIImportClassesWithJDT;
import hai913i.main.java.cli.CLIImportClassesWithSpoon;
import hai913i.main.java.cli.CLIMenu;
import hai913i.main.java.cli.CLIPlotCouplingGraph;
import hai913i.main.java.cli.CLIPrintClustering;
import hai913i.main.java.cli.CLIPrintCouplingGraph;
import hai913i.main.java.cli.CLIPrintIdentification;
import hai913i.main.java.utils.AbsParser;

public class Main extends AbstractMain{
	private static CouplingGraph cGraph;
	private static AbsParser parser;
	private static Clustering cluster;
	private static Identification identification;
	private static CLIMenu menu1 = new CLIMenu();
	private static CLIMenu menu2 = new CLIMenu();
	public static void main(String args[])
	{
		getJavaPath(args);
		CLIImportClassesWithJDT cliImportClassesWithJDT = new CLIImportClassesWithJDT(TEST_PROJECT_PATH);
		CLIImportClassesWithSpoon cliImportClassesWithSpoon = new CLIImportClassesWithSpoon(TEST_PROJECT_PATH);
		menu2.addChoice(cliImportClassesWithJDT);
		menu2.addChoice(cliImportClassesWithSpoon);
		menu2.run();
		if(cliImportClassesWithJDT.getParser() == null)
		{
			parser = cliImportClassesWithSpoon.getParser();
		}
		else{
			parser = cliImportClassesWithJDT.getParser();
		}
		cGraph = new CouplingGraph(parser);
		cluster = new Clustering(cGraph.getGraph());
		
		System.out.println("Génération du graphe de couplage...");
		cGraph.generateCouplingGraph();
		System.out.println("Execution de l'algorithme de clustering hi�rarchique...");
		cluster.clusteringHierarchique();
		
		//Algorithme d'identification
		identification = new Identification(cluster.getDendrogram(), (float)0.05);
		System.out.println("Execution de l'algorithme d'identification...");
		identification.run(cGraph.getGraph());
		menu1.addChoice(new CLIPrintCouplingGraph("Afficher graphe de couplage dans le terminal", cGraph));
		menu1.addChoice(new CLIPlotCouplingGraph("Afficher le graphe de couplage en 2D", cGraph));
		menu1.addChoice(new CLIPrintClustering("Afficher le dendrogramme dans le terminal", cluster));
		menu1.addChoice(new CLIPrintIdentification("Afficher les modules identifi�s dans le terminal", identification));
		menu1.run();
	}
}
