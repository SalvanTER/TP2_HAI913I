package hai913i.main.java.cli;

import hai913i.main.java.main.CouplingGraph;

public class CLIPlotCouplingGraph extends IChoice{
	CouplingGraph cGraph;
	public CLIPlotCouplingGraph(String title, CouplingGraph cGraph) {
		super(title);
		this.cGraph = cGraph;
	}

	@Override
	public void todo() {
		cGraph.plotGraph();
	}

}
