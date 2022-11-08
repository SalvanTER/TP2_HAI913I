package main;

import cli.CLIMenu;
import cli.CLIPlotCouplingGraph;
import cli.CLIPrintClustering;
import cli.CLIPrintCouplingGraph;
import cli.CLIPrintIdentification;
import identification.Identification;
import spoon.processors.CodeGenerationProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.visitors.CtClassVisitor;
import utils.Parser;

public class Main{
	private static CouplingGraph cGraph;
	private static Parser parser;
	private static Clustering cluster;
	private static Identification identification;
	private static CLIMenu menu = new CLIMenu();
	private static CtClassVisitor ctClassVisitor = new CtClassVisitor();
	public static void main(String args[])
	{
		
		System.out.println("Spoon");
		CodeGenerationProcessor processor = new CodeGenerationProcessor("D:\\telechargement_chrome\\mini_project_in_project\\project");
		CodeGenerationProcessor codeGenerationProcessor = (CodeGenerationProcessor) processor;
		codeGenerationProcessor.apply(ctClassVisitor);
		for (CtClass ctClass : ctClassVisitor.getClasses()) {
			System.out.println(ctClass.getSimpleName());
		}
		
		/* 
		System.out.println("Récupération des classes avec l'AST...");
		try {
			parser = new Parser();
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}finally {
			
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
		menu.addChoice(new CLIPrintCouplingGraph("Afficher graphe de couplage dans le terminal", cGraph));
		menu.addChoice(new CLIPlotCouplingGraph("Afficher le graphe de couplage en 2D", cGraph));
		menu.addChoice(new CLIPrintClustering("Afficher le dendrogramme dans le terminal", cluster));
		menu.addChoice(new CLIPrintIdentification("Afficher les modules identifi�s dans le terminal", identification));
		menu.run();
		*/
	}
}
