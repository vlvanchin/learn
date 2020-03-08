package com.my;

public enum EnumSingleton {

	INSTANCE;
	
	public static void sayGreeting() {
		System.out.println("Enum Singleton");
	}
}
