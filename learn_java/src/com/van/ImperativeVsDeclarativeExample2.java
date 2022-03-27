package com.van;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeVsDeclarativeExample2 {

	
	public static void main (String [] args) {
		
		List<Integer> integerList = Arrays.asList(1,2,3,3,5,7,7,8,9,10);
		
		// imperative 
		List<Integer>	uniqueList = new ArrayList<Integer>();
		
		for (Integer integer : integerList) {
			if (!uniqueList.contains(integer)) {
				uniqueList.add(integer);
			}
		}
		
		System.out.println("uniquelist: " + uniqueList);
		
		
		// declarative
		List<Integer> uniqueListX = integerList.stream().distinct().collect(Collectors.toList());
		
		System.out.println("uniqueList : " + uniqueListX);
		
		List<String> stringList = Arrays.asList("a","b","b","d","e","e");
		
		System.out.println("unique string" + stringList.stream().distinct().collect(Collectors.toList()));
		
	    List<Character> charList = Arrays.asList('a','b','b', 'd','e');
	    
	    System.out.println("uniquechar: " + charList.stream().distinct().collect(Collectors.toList()));
				
	}
}
