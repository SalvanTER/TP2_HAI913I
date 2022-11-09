package hai913i.main.java.spoon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.support.reflect.code.CtInvocationImpl;

public class InvocationTree {
    private HashMap<MethodOfClassSpoon, ArrayList<CtInvocationImpl>> invocationTree;
    private ArrayList<MethodOfClassSpoon> methodOfClasses;
    public ArrayList<MethodOfClassSpoon> getMethodOfClasses() {
        return methodOfClasses;
    }
    public void setMethodOfClasses(ArrayList<MethodOfClassSpoon> methodOfClasses) {
        this.methodOfClasses = methodOfClasses;
    }
    public InvocationTree()
    {
        invocationTree= new HashMap<MethodOfClassSpoon, ArrayList<CtInvocationImpl>>();
        methodOfClasses = new ArrayList<MethodOfClassSpoon>();
        
    }
    public void generate(List<CtClass> classes)
    {
        for(CtClass c :  classes)
		{
			for (Object method : c.getMethods().toArray()) {
				CtMethod method2 = (CtMethod)method;
                MethodOfClassSpoon methodOfClass = new MethodOfClassSpoon(c, method2);
                methodOfClasses.add(methodOfClass);
                invocationTree.put(methodOfClass, new ArrayList<CtInvocationImpl>());
				if(method2.getBody() != null)
				{
					for(CtElement e : method2.getBody())
					{
						if(e instanceof CtInvocationImpl)
						{
							CtInvocationImpl m = (CtInvocationImpl)e;
                            invocationTree.get(methodOfClass).add(m);
						}
					}
				}
			}
		}
    }
    public void printInvocationTree()
    {
        for(Map.Entry e : invocationTree.entrySet())
        {
            System.out.println("Class : " + ((MethodOfClassSpoon)e.getKey()).myclass.getSimpleName()  + ", method : "+ ((MethodOfClassSpoon)e.getKey()).method.getSimpleName());
            for(CtInvocationImpl cf : ((ArrayList<CtInvocationImpl>)e.getValue()))
            {
                System.out.println("-" + cf);
                System.out.println("-" + cf.getTarget().getType());
            }
        }
    }
    public HashMap<MethodOfClassSpoon, ArrayList<CtInvocationImpl>> getInvocationTree()
    {
        return invocationTree;
    }
}
