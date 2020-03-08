package com.my;

public class ThreadSafeSingleton {

	private static ThreadSafeSingleton instance;
	
	private ThreadSafeSingleton () {}
	
	public static synchronized ThreadSafeSingleton getInstance() {
		if (instance == null) {
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}
	
	public static void main (String[] args) {
		ThreadSafeSingleton obj = ThreadSafeSingleton.getInstance();
		System.out.println(obj.toString());
		obj = ThreadSafeSingleton.getInstance();
		System.out.println(obj.toString());
	}
}
