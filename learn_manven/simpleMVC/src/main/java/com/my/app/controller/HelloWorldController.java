package com.my.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(ModelMap model) {
		model.addAttribute("greeting","Hello world from spring 4 mvc");
		return "welcome";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/helloagain")
	public String sayHelloAgain(ModelMap model) {
		model.addAttribute("greeting", "Hello again");
		return "welcome";
	}
	
}
