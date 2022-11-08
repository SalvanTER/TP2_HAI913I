package cli;
import main.CouplingGraph;
public class CLIPlotCouplingGraph extends IChoice {
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