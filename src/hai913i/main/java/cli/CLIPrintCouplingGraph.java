package hai913i.main.java.cli;

import hai913i.main.java.main.CouplingGraph;

public class CLIPrintCouplingGraph extends IChoice{
	CouplingGraph cGraph;
	public CLIPrintCouplingGraph(String title, CouplingGraph cGraph) {
		super(title);
		this.cGraph = cGraph;
	}

	@Override
	public void todo() {
		cGraph.printGraph();
	}
	
}
