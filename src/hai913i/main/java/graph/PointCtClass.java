package hai913i.main.java.graph;

import spoon.reflect.declaration.CtClass;

public class PointCtClass extends Point{
    CtClass c;
    public PointCtClass(CtClass c) {
        super(0, 0);
        this.c = c;
    }

    @Override
    public String getName() {
        return c.getSimpleName();
    }

    @Override
    public Object getEntity() {
        return c;
    }

    @Override
    public boolean equalsEntity(Object o) {
        PointCtClass ctClass = (PointCtClass)o;
        return ctClass.getEntity().equals(c);
    }
    
}
