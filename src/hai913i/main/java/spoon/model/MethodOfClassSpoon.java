package hai913i.main.java.spoon.model;

import hai913i.main.java.utils.IMethodOfClass;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class MethodOfClassSpoon implements IMethodOfClass{

	CtClass myclass;
	public CtClass getMyclass() {
        return myclass;
    }
    public void setMyclass(CtClass myclass) {
        this.myclass = myclass;
    }
    CtMethod method;
	
	
	public CtMethod getMethod() {
        return method;
    }
    public void setMethod(CtMethod method) {
        this.method = method;
    }
    public MethodOfClassSpoon(CtClass myclass, CtMethod method) {
		this.myclass = myclass;
		this.method = method;
	}
	@Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MethodOfClassSpoon)) {
            return false;
        }
        MethodOfClassSpoon c = (MethodOfClassSpoon) o;
        return myclass.equals(c.myclass) &&  method.equals(c.method);
    }
}
