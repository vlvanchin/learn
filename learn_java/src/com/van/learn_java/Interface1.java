package com.van.learn_java;

@FunctionalInterface
public interface Interface1 {

	void method1 (String str);
	
	default void log(String str) {
		System.out.println("I1 logging : " + str);
	}
	
	static void print(String str) {
		System.out.println("Printing : " + str);
	}
}


@FunctionalInterface
interface Interface2 {
	void method2();
	
	default void log(String str) {
		System.out.println("I2 logging : " + str);
	}
	
}

