package InternPack;



public class Rectangle extends Shape {
	
	double length;
	double width;
//	double area = calculateArea(length, width);
	@Override
	public double getCalculatePerimeter() {
	return  2*(length+width);
	
	}

	@Override
	public double getCalculateArea() {
		return (length*width);
	}
	

}
