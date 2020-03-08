package com.my;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializedSingletonTest {

	
	public static void main (String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		// serialization of instance
		SerializedSingleton instance = SerializedSingleton.getInstance();
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
		out.writeObject(instance);
		out.close();
		
		// deserialization of stored instance
		ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
		SerializedSingleton instance2 = (SerializedSingleton)in.readObject();
		in.close();
		
		// check to see if the instances are same
		System.out.println(instance.equals(instance2));
		System.out.println(instance.toString());
		System.out.println(instance2.toString());
	}
}
