package com.my;

public class SampleClass {
	public static void main(String[] args) {
		System.out.println("Sample Class");
		
		print5Times();
	}
	
	public static void print5Times () {
		for (int i=0; i < 5;i++) {
			System.out.println("print : " + i);
		}
	}
 
}
