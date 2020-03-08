package com.my;

import java.lang.reflect.Constructor;

/* 
 * This test class uses Reflection to destroy all the above Singleton implementation
 * approaches.
 * 
 */
public class ReflectionSingletonTest {

	public static void main (String[] args) {
		EagerSingleton instanceOne = EagerSingleton.getInstance();
		EagerSingleton instanceTwo = null ;
		try {
			Constructor[] constructors = EagerSingleton.class.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
				// below code will create the second object (no more singleton)
				constructor.setAccessible(true);
				instanceTwo = (EagerSingleton)constructor.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(instanceOne.toString());
		System.out.println(instanceTwo.toString());
	}
}
