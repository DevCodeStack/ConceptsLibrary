package com.devcodestack.core.concepts.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * Stream creation from primitive and wrapper types
 * Various inetrmediate and terminal function
 * 
 */
public class StreamConcept01 {

	public static void main(String[] args) {
		
		int[] primitiveType = {4,3,2,9};
		Integer[] wrapperType = {4,3,2,9};
		
		/* Building stream out of an array */
//		Arrays.stream(primitiveType).forEach(System.out::println);
//		Stream.of(primitiveType).forEach(System.out::println); // Primitive type doesn't works with Stream.of()
		
//		Arrays.stream(wrapperType).forEach(System.out::println);
//		Stream.of(wrapperType).forEach(System.out::println);

		List<Integer> nums = Arrays.asList(4, 8, 0, 7, 1, 18, 13, 97, 45, 78, 36, 0, 4);
		
		nums.stream()
			.filter(n -> n%2 == 0)
			.map(n -> n*3)
			.distinct()
			.sorted()
			.peek(System.out::println)
			.sorted((a,b) -> b-a)
			.limit(4)
			.skip(1)
//			.reduce((a, b) -> a + b);
			.forEach(System.out::println);
		
		
		/* Primitive type produces IntStream
		 * whereas Wrapper type produces wrapped stream i.e; Integer Stream*/
		Arrays.stream(primitiveType)
		.filter(n -> n%2 == 0)
		.map(n -> n*3)
		.sum();

		Arrays.stream(wrapperType)
		.filter(n -> n%2 == 0)
		.mapToInt(n -> n*3)
		.sum();
		
	}

}
