package com.vlv.sample;

import java.util.Arrays;
import java.util.function.Supplier;

public class LambdaDemo {

	public static void main(String[] args) {
		// sample 1
		Arrays.asList("a","b","c").forEach(e -> System.out.println (e));
		
		// sample 2
		Arrays.asList("a","b","c").forEach((String e) -> {
			System.out.println(e);
		});
		
		// sample 3
		final String separator = ",";
		Arrays.asList("a","b","c").forEach((String e) -> {
			System.out.println(e + separator);
		});

		// sample 4 
		// lambda return a value
		Arrays.asList("a","x","c").sort((e1,e2) -> {
			int result = e1.compareTo(e2);
			System.out.println(result);
			return result;
		});
		
		// sample 5
		Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
		System.out.println( defaulable.notRequired());
		
		defaulable = DefaulableFactory.create( OverridableImpl::new);
		System.out.println(defaulable.notRequired());
	}

}

@FunctionalInterface
interface Functional {
	void method();
}

interface Defaulable {
	default String notRequired() {
		return "Default implementation";
	}
}

interface DefaulableFactory {
	
	static Defaulable create(Supplier <Defaulable> supplier) {
		return supplier.get();
	}
}

class DefaultableImpl implements Defaulable {
	
}

class OverridableImpl implements Defaulable {
	@Override
	public String notRequired() {
		return  "not required overwritten";
	}
}

