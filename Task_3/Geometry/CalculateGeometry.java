package InternPack;

import java.util.Scanner;

public class CalculateGeometry {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Press 1 for Rectangle geometry");
		System.out.println("Press 2 for Circle geometry");
		System.out.println("Press 3 for Triangle geometry");
		System.out.println("chose which geometry shape you required");
		int choice=scan.nextInt();
		switch (choice) {
			case 1: {
				Rectangle rectangle = new Rectangle();
				System.out.println("Enter the lenght of rectangle");
				rectangle.length=scan.nextDouble();
				System.out.println("Enter the lenght of width");
				rectangle.width=scan.nextDouble();
				System.out.println("Area of recyangle is:"+rectangle.getCalculateArea());
				System.out.println("Perimeter of recyangle is:"+rectangle.getCalculatePerimeter());
				break;
			}
			case 2: {
				Circle circle = new Circle();
				System.out.println("Enter the radius of circle");
				circle.radius=scan.nextDouble();
				System.out.println("Area of circle is :"+circle.getCalculateArea());
				System.out.println("Perimeter of circle is :"+circle.getCalculatePerimeter());
				break;
			}
			case 3 :{
				Triangle triangle = new Triangle();
				System.out.println("Enter the  base");
				triangle.base=scan.nextDouble();
				System.out.println("Enter the height");
				triangle.height = scan.nextDouble();
				System.out.println("Enter the  side length");
				triangle.length = scan.nextDouble();
				System.out.println("Enter the anotherSide length");
				triangle.anotherSide=scan.nextDouble();
				System.out.println(triangle.getCalculateArea());
				System.out.println(triangle.getCalculatePerimeter());
				break;
			}
			default :
				System.out.println("Please enter the anyone of the above values");
		
		}
	

	}

}
