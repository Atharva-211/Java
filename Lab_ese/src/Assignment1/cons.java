package Assignment1;

class productt {
	private String name;
	private int prize;
	private double quantity;
	
	public productt() {
		this.name = "atharva";
		this.prize = 200;
		this.quantity = 1.5;
	}
	
	public productt(String name, int prize, double quantity) {
		this.name = name;
		this.prize = prize;
		this.quantity = quantity;
	}
	
	public void displayy() {
		System.out.println("name : " + name);
		System.out.println("prize : " + prize);
		System.out.println("quantity : " + quantity);
	}
}

public class cons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		productt defauntproductt = new productt();
		System.out.println("Default product");
		defauntproductt.displayy();
		
		productt para = new productt("hii", 10, 10.1);
		System.out.println("para");
		para.displayy();

	}

}