package InternPack;

public class Triangle extends Shape {
	double length;
	double base;
	double height;
	double anotherSide;
	@Override
	public double getCalculateArea() {
		double area =0.5*base*height;
		return area;
	}

	@Override
	public double getCalculatePerimeter() {
		return length+base+anotherSide;
	}
	
}
