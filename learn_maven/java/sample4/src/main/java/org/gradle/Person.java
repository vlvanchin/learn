package org.gradle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person {
    private final String name;
	Logger logger = LogManager.getLogger(Person.class);
    
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
    	logger.info("returns Person's name");
        return name;
    }
    
    public static void main(String[] args) {
    	Person p = new Person("dada");
    	System.out.println("name of the person object P is " + p.getName());
    	
    }

}
