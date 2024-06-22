//1.	Design a class named "Product" with default, and parameterized constructors. 
//	a.	Default constructor initializes default values
//	b.	the parameterized constructor allows custom initialization
//
//	Implement methods to display product details. Test the class by creating instances using 
//each constructor to make sure everything works correctly and data is handled properly.


package assignment;
//instead of class product used a1
public class a1 {
	private String name;
	private double price;
	private int quantity;
	
	//Default constructor
	public a1() {
		// initialize default values 
		this.name = "Default product ";
		this.price = 0.0;
		this.quantity= 0;
	}
	
	//parameterised constructor
	public a1 (String name, double price, int quantity) {
		//custom initialize
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	//method to display product details
	public void display() {
		System.out.println("Product name :" + name);
		System.out.println("price :$" + price);
		System.out.println("quantity :" + quantity);
		System.out.println("..............................");
	}

	public static void main(String[] args) {
		// testing default constructor
		a1 defaultProduct = new a1();
		System.out.println("default product details :");
		defaultProduct.display();
		
		//testing parameterised constructor
		a1 CostomProduct = new a1("Costom Product ", 25.1, 10);
		System.out.println("custom product details :");
		CostomProduct.display();
	}

}
