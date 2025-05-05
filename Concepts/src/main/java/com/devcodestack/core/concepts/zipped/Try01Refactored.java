package com.devcodestack.core.concepts.zipped;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Try01Refactored {
	
	public static void main(String[] args) {
		String str = "depakbiswalcde"; //abcdefabcde
		
		List<Character> charList = new ArrayList<>();
		String maxString = "";
		
		long len = Arrays.asList(str.toCharArray()).stream().distinct().count();
		if(len != str.length()) {
			
			/* Merged two loops into one...Try to avoid using continue when merging loops into one */
			for(int j,i=j=0; i<str.length(); i++) { //j,i=j=0
				
				if(!charList.contains(str.charAt(i))) {
					charList.add(str.charAt(i));
				} else {
					maxString = charList.size() > maxString.length() ? 
							charList.stream().map(Object::toString).collect(Collectors.joining()) 
							: maxString;
					charList.clear();
					charList.add(str.charAt(i));
				}
				
				/* Necessary condition to facilitate updating of counter for a merged loop */
				if(i==str.length()-1 && j<str.length()-1) {
					maxString = charList.size() > maxString.length() ? 
							charList.stream().map(Object::toString).collect(Collectors.joining()) 
							: maxString;
					charList.clear();
					j++;
					i=j-1;
				}
			}
		} else {
			maxString = str;
		}
		
		System.out.println(maxString);

	}
}
