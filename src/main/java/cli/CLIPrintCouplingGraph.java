package cli;

import main.CouplingGraph;

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
