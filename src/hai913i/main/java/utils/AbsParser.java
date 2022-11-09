package hai913i.main.java.utils;

import java.util.List;

import hai913i.main.java.graph.Point;

public interface AbsParser {

    public List<Point> getClasses();
    public int countMethodsInClass(AbsMethodOfClass mc, Point p);
    public List<AbsMethodOfClass> getMethodsOfClass(Point c1);
}
