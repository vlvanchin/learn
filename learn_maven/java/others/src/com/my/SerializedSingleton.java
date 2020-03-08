package com.my;

import java.io.Serializable;

public class SerializedSingleton implements Serializable{
	
	private static final long serialVersionUID = -7604766932017737115L;
	
	private SerializedSingleton() {}
	
	private static class SingletonHelper {
		private static final SerializedSingleton INSTANCE = new SerializedSingleton();
	}

	public static SerializedSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	// this fixes the deserialization to return the same serialized object
	protected Object readResolve() {
		return getInstance();
	}

}
