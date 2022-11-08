package hai913i.main.java.utils;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class MethodOfClass {

	TypeDeclaration myclass;
	MethodDeclaration method;
	
	
	public MethodOfClass(TypeDeclaration myclass, MethodDeclaration method) {
		this.myclass = myclass;
		this.method = method;
	}
	@Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MethodOfClass)) {
            return false;
        }
        MethodOfClass c = (MethodOfClass) o;
        return myclass.equals(c.myclass) &&  method.equals(c.method);
    }
}
