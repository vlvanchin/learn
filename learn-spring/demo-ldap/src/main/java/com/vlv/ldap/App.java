package com.vlv.ldap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
/** 
 *  Simple web controller
 */

@RestController
class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "Welcome to the home page!";
	}
}