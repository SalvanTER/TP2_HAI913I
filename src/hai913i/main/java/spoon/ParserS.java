package hai913i.main.java.spoon;

import java.util.ArrayList;
import java.util.List;

import hai913i.main.java.graph.Point;
import hai913i.main.java.graph.PointCtClass;
import hai913i.main.java.spoon.model.InvocationTree;
import hai913i.main.java.spoon.model.MethodOfClassSpoon;
import hai913i.main.java.spoon.processors.CodeGenerationProcessor;
import hai913i.main.java.spoon.visitors.CtClassVisitor;
import hai913i.main.java.utils.IMethodOfClass;
import hai913i.main.java.utils.IParser;
import spoon.reflect.declaration.CtClass;
import spoon.support.reflect.code.CtInvocationImpl;

public class ParserS implements IParser{
    CodeGenerationProcessor processor;
    private static CtClassVisitor ctClassVisitor;
    InvocationTree invocationTree;
    List<Point> classes;
    
    public ParserS(String projectpath)
    {	
		invocationTree = new InvocationTree();
        ctClassVisitor = new CtClassVisitor();
        classes = new ArrayList<Point>();
        String pp = "D:\\telechargement_chrome\\mini_project_in_project\\project";
        processor = new CodeGenerationProcessor(projectpath);
    }
    public void run()
    {
        processor.apply(ctClassVisitor);
        
        for(CtClass c : ctClassVisitor.getClasses())
        {
            classes.add(new PointCtClass(c));
        }
        invocationTree.generate(ctClassVisitor.getClasses());
    }
    @Override
    public List<Point> getClasses() {
        return classes;
    }
    @Override
    public int countMethodsInClass(IMethodOfClass mc, Point p) {
        int res = 0;
		ArrayList<CtInvocationImpl> mis = invocationTree.getInvocationTree().get(mc);
		for(CtInvocationImpl mi : mis)
		{
			if(p.getName().equals(getObjectType(mi)))
			{
				res++;
			}
		}
		return res;
    }

    private String getObjectType(CtInvocationImpl mi) {
        return mi.getTarget().getType().getSimpleName();
    }
    @Override
    public List<IMethodOfClass> getMethodsOfClass(Point c1) {
        ArrayList<IMethodOfClass> mcs = new ArrayList<IMethodOfClass>();
		for(MethodOfClassSpoon mc : invocationTree.getMethodOfClasses())
		{
			if(mc.getMyclass().equals(c1.getEntity()))
			{
				mcs.add(mc);
			}
		}
		return mcs;
    }
    public ArrayList<MethodOfClassSpoon> getMethodsOfClasses()
    {
        return invocationTree.getMethodOfClasses();
    }
}
