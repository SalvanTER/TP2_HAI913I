package hai913i.main.java.graph;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class PointTypeDeclaration extends Point{
    TypeDeclaration td;
    public PointTypeDeclaration(TypeDeclaration td) {
        super(0, 0);
        this.td = td;
    }
    public TypeDeclaration getTd() {
		return td;
	}
    
    public void setTd(TypeDeclaration td) {
		this.td = td;
	}
    @Override
    public String getName() {
        return td.getName().toString();
    }
    @Override
    public Object getEntity() {
        return this.getTd();
    }
    @Override
    public boolean equalsEntity(Object o) {
        PointTypeDeclaration t = (PointTypeDeclaration)o;
        return t.getEntity().equals(td);
    }
}
