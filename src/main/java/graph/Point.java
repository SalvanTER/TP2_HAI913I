package graph;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Point {
	TypeDeclaration td;
	float x;
	float y;
	boolean printed = false;
	public TypeDeclaration getTd() {
		return td;
	}
	public boolean isPrinted()
	{
		return printed;
	}
	public void printed() {
		printed = true;
	}
	public void setTd(TypeDeclaration td) {
		this.td = td;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Point(TypeDeclaration td, float x, float y) {
		super();
		this.td = td;
		this.x = x;
		this.y = y;
	}
}
