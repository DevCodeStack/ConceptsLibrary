package com.devcodestack.core.concepts.streams;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Formatting Stream results based on Grouping, Aggregation and Partitioning
 * 
 */
public class StreamConcept02 {
	
	public static void main(String args[]) {
		
		record Product ( String name, String category, Double price ) {};
		
		var products = Stream.of(new Product("TV", "Electronics", 1000.00), 
								new Product("Washing Machine", "Electronics", 2500.00),
								new Product("Mixer", "Electronics", 460.00),
								new Product("Lamp", "Home Decor", 50.00),
								new Product("Door screen", "Home Decor", 100.00),
								new Product("Desk", "Furniture", 600.00),
								new Product("Table", "Furniture", 350.00));
		
		/* Group by category */
//		var groupByCategory = products.collect(Collectors.groupingBy(Product::category));
//		System.out.println("groupByCategory: " + groupByCategory);
		
		/* No of products per Category */
//		var groupByCategoryAndCounting = products.collect(Collectors.groupingBy(Product::category,
//				Collectors.counting()));
//		System.out.println("groupByCategoryAndCounting: " + groupByCategoryAndCounting);
		
		/* Total cost of products per Category */
//		var aggregateByCategoryAndSummingPrice = products.collect(Collectors.groupingBy(Product::category,
//				Collectors.summingDouble(Product::price)));
//		System.out.println("aggregateByCategoryAndSummingPrice: " + aggregateByCategoryAndSummingPrice);
		
		/* Average cost of products per Category */
//		var aggregateByCategoryAndAveragingPrice = products.collect(Collectors.groupingBy(Product::category,
//				Collectors.averagingDouble(Product::price)));
//		System.out.println("aggregateByCategoryAndAveragingPrice: " + aggregateByCategoryAndAveragingPrice);
		
		/* Partition by price */
		var partioningByPrice = products.collect(Collectors.partitioningBy(product -> product.price>450));
		System.out.println("partioningByPrice: " + partioningByPrice);
		
	}

}
