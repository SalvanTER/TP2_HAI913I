package graph;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import processing.core.PApplet;
public class Graph extends PApplet {
    private static ArrayList<TypeDeclaration> nodes;

    private static ArrayList<Link> links;

    private static int sizeNodes;

    ArrayList<Point> listPoints = new ArrayList<Point>();

    public Graph() {
        super();
        if ((links == null) && (nodes == null)) {
            nodes = new ArrayList<TypeDeclaration>();
            links = new ArrayList<Link>();
        }
    }

    public void addNode(TypeDeclaration node) {
        for (TypeDeclaration t : nodes) {
            if (node.equals(t)) {
                return;
            }
        }
        nodes.add(node);
    }

    public void addLink(Link link) {
        for (Link l : links) {
            if (l.getG1().equals(link.getG1()) && l.getG2().equals(link.getG2())) {
                return;
            }
        }
        links.add(link);
    }

    @Override
    public String toString() {
        String res = "";
        for (Link l : links) {
            res += ((((l.getG1().getName().toString() + " is connected to ") + l.getG2().getName().toString()) + " with weight ") + l.getWeight()) + " \n";
        }
        return res;
    }

    public ArrayList<TypeDeclaration> getNodes() {
        return nodes;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public float getDistanceBetweenNodes(TypeDeclaration t1, TypeDeclaration t2) {
        float res = 0;
        for (Link l : links) {
            if (l.getG1().equals(t1) || l.getG1().equals(t2)) {
                if (l.getG2().equals(t1) || l.getG2().equals(t2)) {
                    return l.getWeight();
                }
            }
        }
        return res;
    }

    public void plot2d() {
        sizeNodes = nodes.size();
        PApplet.main(Graph.class.getName());
    }

    @Override
    public void settings() {
        size(700, 700, graph.FX2D);
        sizeNodes = nodes.size();
        smooth(16);
    }

    @Override
    public void setup() {
        background(150);
        float sizeCircle = (50 * (2 * PI)) / sizeNodes;
        System.out.println(sizeCircle);
        float l = min((width / 2) - sizeCircle, (height / 2) - sizeCircle) - 20;
        int i = 0;
        for (TypeDeclaration t : nodes) {
            noStroke();
            float posX = ((width / 2) - 1) + ((float) (l * cos(((float) (((2 * PI) / sizeNodes) * i)))));
            float posY = ((height / 2) - 1) + ((float) (l * sin(((float) (((2 * PI) / sizeNodes) * i)))));
            listPoints.add(new Point(t, posX, posY));
            i++;
        }
        for (Link li : links) {
            Point p1 = getPoint(li.getG1());
            Point p2 = getPoint(li.getG2());
            stroke(255);
            if (li.getWeight() != 0) {
                strokeWeight(10 * li.getWeight());
                line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                fill(200, 50, 50);
                int textSize = 12;
                textSize(textSize);
            }
        }
        for (Point t : listPoints) {
            printSommet(t, sizeCircle);
        }
    }

    private void printSommet(Point p, float sizeCircle) {
        if (p.isPrinted())
            return;

        p.printed();
        fill(255);
        circle(p.getX(), p.getY(), sizeCircle);
        fill(50, 50, 200);
        int textSize = 15;
        textSize(textSize);
        String text = p.getTd().getName().toString();
        text(p.getTd().getName().toString(), p.getX() - ((text.length() * textSize) / 4), p.getY());
    }

    private Point getPoint(TypeDeclaration t) {
        for (Point p : listPoints) {
            if (p.getTd().equals(t)) {
                return p;
            }
        }
        return null;
    }
}