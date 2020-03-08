package com.my;

public class LazySingleton {

	private static LazySingleton inst1;
	
	private LazySingleton() {
		System.out.println("Singleton object created");
	}
	
	public static LazySingleton getInstance() {
		if (inst1 == null) {
			inst1 = new LazySingleton();
		}
		return inst1;
	}
	
	public static void main(String[] args) {
		LazySingleton obj = LazySingleton.getInstance();
		System.out.println(obj.toString());
		obj = LazySingleton.getInstance();
		System.out.println(obj.toString());
	}
}
