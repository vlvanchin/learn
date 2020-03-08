package com.my;

// this class instance is create at the time of class loading into the JVM

/*
 * If your singleton class is not using a lot of resources, this is the approach
 * to use. But in most of the scenarios, Singleton classes are created for 
 * resources such as File System, Database connections etc and we should 
 * avoid the instantiation until unless client calls the getInstance method. 
 * Also this method doesn’t provide any options for exception handling.
 */

public class EagerSingleton {

	private static final EagerSingleton instance = new EagerSingleton();
	
	// constructor not available for external world
	private EagerSingleton() {
		System.out.println("creates eagersingleton object" );
	}
	
	// only way to access the instance
	public static EagerSingleton getInstance() {
		return instance;
	}
	
	public static void main(String [] args) {
		System.out.println(EagerSingleton.getInstance());
		System.out.println(EagerSingleton.getInstance());
	}
}
