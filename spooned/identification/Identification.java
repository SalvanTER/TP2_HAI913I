package identification;
import dendrogram.Cluster;
import graph.Graph;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.TypeDeclaration;
public class Identification {
    private Cluster den;

    private float cp;

    private ArrayList<Cluster> modules;

    public Identification(Cluster den, float cp) {
        this.den = den;
        this.cp = cp;
        modules = new ArrayList<Cluster>();
    }

    public void run(Graph g) {
        recursivePathDendrogram(den, g);
    }

    private void recursivePathDendrogram(Cluster c, Graph g) {
        if (c.getNodes().size() == 1) {
            return;
        }
        if (c.meanNodesCouplage(g) > cp) {
            modules.add(c);
        } else {
            recursivePathDendrogram(c.getLeft(), g);
            recursivePathDendrogram(c.getRight(), g);
        }
    }

    public void printResult() {
        int i = 0;
        for (Cluster c : modules) {
            i++;
            System.out.println(("Module " + i) + " compos? des classes suivantes : ");
            for (TypeDeclaration t : c.getNodes()) {
                System.out.println("-" + t.getName().toString());
            }
        }
    }
}