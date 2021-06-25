package com.van.azure.springbootsampleactivedirectorybackend.security.controller;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
   
   @RequestMapping(value = "/Policies", method = RequestMethod.GET)
   public String getPolicy(Authentication authentication) {
	   System.out.println(authentication);
	   Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	   System.out.println(authorities);
	   DefaultOidcUser principal =  (DefaultOidcUser) authentication.getPrincipal();
	   System.out.println(principal.getName());
	   //       UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//       System.out.println("Principal: " + userPrincipal.getSubject());        
//
//       Map<String, Object> map = new LinkedHashMap<>();
//       map = (Map<String, Object>) userPrincipal.getClaims();
//
//       System.out.println("Username: " + map.get("name"));
//       System.out.println("Email: " + map.get("upn"));
       return "Hello";
   }
   
}
