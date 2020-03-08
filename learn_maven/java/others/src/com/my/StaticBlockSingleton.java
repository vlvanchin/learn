package com.my;


/*
 * Similar to the Eager Singleton, but the instance of the class is done by
 * static block and provides exception handling
 * 
 */

public class StaticBlockSingleton {

	private static StaticBlockSingleton instance;
	
	private StaticBlockSingleton () {
		System.out.println("creates a singleton object");
	}
	
	// static block initialization for exception handling
	static {
		try {
			instance = new StaticBlockSingleton();
		} catch (Exception e) {
			throw new RuntimeException("exception occured while creating instance");
		}
	}
	
	public static StaticBlockSingleton getInstance() {
		return instance;
	}
	
	public static void main (String[] args) {
		System.out.println(StaticBlockSingleton.getInstance());
		System.out.println(StaticBlockSingleton.getInstance());
	}
}
