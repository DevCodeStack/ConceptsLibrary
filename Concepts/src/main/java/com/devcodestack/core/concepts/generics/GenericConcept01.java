package com.devcodestack.core.concepts.generics;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;


/*
 * Generic Function
 * 
 * Generics work only with reference Types and not primitive types
 * 
 * Code Reusability
 * Individual Type Casting is not needed
 * Type Safety
 * 
 * Ref: https://www.geeksforgeeks.org/generics-in-java/
 * Ref: https://www.baeldung.com/java-generics
 */
public class GenericConcept01 {
	
	public static void main(String[] args) {
		
		printElements(getElements(new Integer[] {1,2,3,4}));
		
		printElements(getElements(new String[] {"Deepak", "Nagen", "Aftab"}));
		
		printElements(getElements(new Character[] {'a', 'b', 'c'}));
		
		printElements(convert(getElements(new String[] {"Deepak", "Nagen", "Aftab"}), str -> str.length()));
		
	}
	
	/* The <T> in the method signature implies that the method will be dealing with generic type T */
	public static <T> void printElements(List<T> eles) {
		
		System.out.println("Printing elements of type: " + eles.get(0).getClass());
		eles.stream().forEach(System.out::println);
	}
	
	/* The <T> in the method signature implies that the method will be dealing with generic type T */
	public static <T> List<T> getElements(T[] t) {
		
		return Stream.of(t).toList();
	}
	
	/* Below method signature will be dealing with generic type T and G	 */
	public static <T, G> List<G> convert(List<T> eles, Function<T, G> mapper){
		
		return eles.stream().map(mapper).toList();
	}
}
