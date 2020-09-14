package com.van.learn_java;

public class DefaultMethodImplementer implements Interface1, Interface2 {

	@Override
	public void log(String str) {
		System.out.println("DefaultMethodImplementer logging: " + str);
		Interface1.print("1234");
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method1(String str) {
		System.out.println("method1: " + str);
		
	}

	
	public static void main (String[] args) {
		DefaultMethodImplementer dmi = new DefaultMethodImplementer();
		dmi.log("hello");
		dmi.method2();
		dmi.method1("12345");
	}
}
	