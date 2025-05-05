package com.devcodestack.core.concepts.zipped;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Try01 {

	public static void main(String[] args) {
		String str = "depakbiswalcde"; //abcdefabcde
		
		List<Character> charList = new ArrayList<>();
		String maxString = "";
		
		long len = Arrays.asList(str.toCharArray()).stream().distinct().count();
		if(len != str.length()) {
			
			for(int j=0; j<str.length(); j++) {
				for(int i=j; i<str.length(); i++) {
					
					if(!charList.contains(str.charAt(i))) {
						charList.add(str.charAt(i));
						continue;
					}
					
					var s = charList.stream().map(Object::toString).collect(Collectors.joining());
					maxString = s.length() > maxString.length() ? s : maxString;
					charList.clear();
					charList.add(str.charAt(i));
				}
				
				var s = charList.stream().map(Object::toString).collect(Collectors.joining());
				maxString = s.length() > maxString.length() ? s : maxString;
				charList.clear();
			}
		} else {
			maxString = str;
		}
		
		System.out.println(maxString);

	}

}
