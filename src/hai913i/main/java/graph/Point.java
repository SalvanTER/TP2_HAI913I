package hai913i.main.java.graph;

public abstract class Point {
	float x;
	float y;
	boolean printed = false;
	public boolean isPrinted()
	{
		return printed;
	}
	public void printed() {
		printed = true;
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
	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	public abstract String getName();
	public abstract Object getEntity();
	public abstract boolean equalsEntity(Object o);
	@Override
	public boolean equals(Object o) { 
        return equalsEntity(o);
    }
}
