package com.devcodestack.core.concepts.generics;

/*
 * Generic Class
 * 
 * Ref: https://www.geeksforgeeks.org/generics-in-java/
 * Ref: https://www.baeldung.com/java-generics
 */
public class GenericConcept02 {
	
	public static void main(String[] args) {
		
		GenericEmbed<Integer> embed01 = new GenericEmbed<>(10);
		GenericEmbed<String> embed02 = new GenericEmbed<>("Deepak");
		
		System.out.println(embed01.get());
		System.out.println(embed02.get());
		
	}
}

class GenericEmbed<T> {
	
	private T obj;
	
	GenericEmbed(T obj) {
		this.obj = obj;
	}
	
	public T get() {
		return this.obj;
	}
	
}
