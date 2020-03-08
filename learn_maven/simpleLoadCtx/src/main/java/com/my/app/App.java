package com.my.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.common.Greeting;
import com.my.common.HelloWorld;
import com.my.config.AppConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// load the spring context
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	
    	HelloWorld obj =  (HelloWorld) ctx.getBean("helloBean");

    	obj.printHello();
    	obj.setName("test user 1");
    	obj.printHello();
    	
    	ApplicationContext ctx2 = new AnnotationConfigApplicationContext(AppConfig.class);
    	Greeting greeting = (Greeting) ctx2.getBean("greeting");
    	greeting.printGreeting();
    }
}
	