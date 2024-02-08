package InternPack;

public class Circle extends Shape {
	double radius;
	@Override
	public double getCalculateArea() {
		return Math.PI*radius*radius ;
	}

	@Override
	public double getCalculatePerimeter() {
		return 2*Math.PI*radius;
	}

}
