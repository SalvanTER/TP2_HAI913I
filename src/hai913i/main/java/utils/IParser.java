package hai913i.main.java.utils;

import java.util.List;

import hai913i.main.java.graph.Point;

public interface IParser {
    public List<Point> getClasses();
    public int countMethodsInClass(IMethodOfClass mc, Point p);
    public List<IMethodOfClass> getMethodsOfClass(Point c1);
}
