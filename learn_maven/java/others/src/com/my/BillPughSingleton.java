package com.my;

/*
 * Uses a inner static helper class to create singleton objects
 * 
 * Most frequently used pattern for Singleton objectss
 */

public class BillPughSingleton {

	private BillPughSingleton() {}
	
	private static class SingletonHelper {
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}
	
	public static BillPughSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
}
