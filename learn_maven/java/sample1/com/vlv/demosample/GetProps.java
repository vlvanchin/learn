package com.vlv.demosample;

import com.vlv.sample.SampleGreet;

import java.lang.*;
import java.security.*;

public class GetProps {

	public static void main (String [] args) {
		String s;
		try {
		        SampleGreet sampleGreet = new SampleGreet();
                        sampleGreet.greet();
                        System.out.println ("-------------------");	
                        System.out.println("os.name value");
			s = System.getProperty("os.name", "not given");
			System.out.println("os is " + s);

			System.out.println("about java.version");
			s = System.getProperty("java.version", "not given");
			System.out.println("java version " + s);

			System.out.println("about user home");
			s = System.getProperty("user.home", "not given");
			System.out.println("user home " + s);

			System.out.println("about java home");
			s = System.getProperty("java.home","not given");
			System.out.println("java home " + s);
		} catch (Exception e) {
			System.err.println("caught exception " + e.toString());
		}
	}
}
