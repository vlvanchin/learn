package com.vlv.subproj;

public class Greeting {

  public String greetPerson (String person) {
    return "Hello, " + person;
  }

  public static void main (String[] args) {
    Greeting o = new Greeting() ;
    String msg = o.greetPerson("vanchin");
    System.out.println(msg);
  }
}
