package com.van.azure.springbootsampleactivedirectorybackend.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;

@RestController
public class HelloController {
   @Autowired
   @PreAuthorize("hasRole('ROLE_Users')")
   @RequestMapping("/")
   public String helloWorld() {
      return "Hello World!";
   }
   
   @Autowired
   @PreAuthorize("hasRole('ROLE_Users')")
   @RequestMapping("/greeting")
   public String greeting() {
	   return "Hello there!, <br/>Good morning!";
   }
   
}
