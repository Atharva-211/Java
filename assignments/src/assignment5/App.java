package assignment5;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		ArrayList<String> fruits = new ArrayList<>();
		
		fruits.add("Apple");
		fruits.add("Pear");
		fruits.add("Grapes");
		
		System.out.println("Fruits: " + fruits);
		
		System.out.println("Fruits in the list");
		
		for(String fruit: fruits)
		{
			System.out.println(fruit);
			
		}
		
		System.out.println("First Fruit: " + fruits.get(0));
		System.out.println("Second Fruit: " +  fruits.get(1));
		
		
		fruits.set(1, "Mango");
		
		System.out.println(fruits);
		
		String removed_fruit = "Grapes";
		
		fruits.remove(removed_fruit);
		System.out.println("List of fruits after removing "+ removed_fruit + fruits);
		
		java.util.Collections.sort(fruits);
		System.out.println("Sorted list of fruits: " + fruits);
		
		if(fruits.contains(removed_fruit))
		{
			System.out.println("Fruit Exist!");
		}else {
			
			System.out.println("Fruit Not present");
		}
		
		
		if(fruits.isEmpty())
		{
			System.out.print(true);
		}else {
			System.out.println("Empty");
		}
		
		fruits.clear();
        System.out.println("List of fruits after clearing: " + fruits);
	}

}
