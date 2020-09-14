package com.van.learn_java;

public class FunctionalInterfaceDemo {

	public static void main (String[] args) {
		
		Runnable r = new Runnable () {
			public void run () {
				System.out.println("My runnable");
			}
		};
		
		Runnable r1 = () -> {
			System.out.println("My runnable r1");
		};
		
		r.run();
		r1.run();
		
		Interface1 i1 = new Interface1 () {
			public void method1 (String str) {
				System.out.println(str);
			}
		};
		
		i1.method1("testing");
		
		Interface1 i2 = (s) -> System.out.println("Hello : " + s);
		
		i2.method1("ABCD");
	}
}
