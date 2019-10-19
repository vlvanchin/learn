package com.vlv.sample;

public class NaiveSingleton {

	private static NaiveSingleton instance;
	
	private NaiveSingleton() {
		
	}
	
	public static NaiveSingleton getInstance() {
		if (instance == null) {
			instance = new NaiveSingleton();
		}
		return instance;
	}
	
	public static void main(String [] a) {
		NaiveSingleton obj = NaiveSingleton.getInstance();
		System.out.println("object reference: " + obj);
		
		NaiveSingleton obj2 = NaiveSingleton.getInstance();
		System.out.println("object reference: " + obj2);
		
		String envValue = System.getenv("TEST_ME");
		System.out.println("envValue: " + envValue);
	}
}
