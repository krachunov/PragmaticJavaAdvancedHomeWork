package edu.pragmatic.homework.reflection;

public class Demo {
	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException {
		Pet cat = new Pet(7, "Cat");
		cat.setChildren(new Pet[2]);
		cat.getChildren()[0] = new Pet(1, "little kitty");
		cat.getChildren()[1] = new Pet(2, "bigger kitty");
		Debug.printInstanceInfo(cat);
		
		Debug debug = new Debug();
	
	}
}
