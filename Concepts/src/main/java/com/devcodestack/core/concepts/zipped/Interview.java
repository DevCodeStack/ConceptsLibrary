package com.devcodestack.core.concepts.zipped;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Interview {

	public static void main(String[] args) {
		
		//Functional Interface
		
		Demo demo = (a1, a2) -> a1 + a2;
		
		System.out.println(demo.apply(10, 20));
		
		List<Integer> lii = new CopyOnWriteArrayList<>();

	}

}

@FunctionalInterface
interface Demo{
	
	public Integer apply(int a1, int a2);
	
	
}
