package com.my.concurrency;

public class HelloThread extends Thread {

	public static void main(String[] a) {
		new HelloThread().start();
	}
	
	public void run() {
		System.out.println("this is from thread");
	}
}
