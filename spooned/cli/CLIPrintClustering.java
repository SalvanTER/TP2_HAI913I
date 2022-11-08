package cli;
import main.Clustering;
public class CLIPrintClustering extends IChoice {
    Clustering c;

    public CLIPrintClustering(String title, Clustering c) {
        super(title);
        this.c = c;
    }

    @Override
    public void todo() {
        c.printResult();
    }
}