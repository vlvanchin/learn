package com.my.concurrency;

public class SleepMessages {
	public static void main (String[] args) {
		String messages[] = {
				"Susila eats oats",
				"Bagvath eats oats bar",
				"Subbash eats oats too",
				"vanchin eats oats"
		};
		
		for (String s:messages) {
			// pause for 4 secs
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// print a message
			System.out.println(s);
		}
	}
}
