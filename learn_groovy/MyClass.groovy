// this is my first groovy prog
/*
 * this is a multi line comment
 * 
*/
println('Hello World!');

println 'hello' /* a multiline comment
                   at the end of a statement
  test */
println 1 /* one */ + 2 /* two */

/** 
 *  a class description
 */
class Person {
  /** the name of  the person */
  String name

  /** 
   * 
   * creates a greeting message for certain person.
   * 
   * @param otherPerson the person to greet
   * @return a greeting message
   */
  String greet (String otherPerson) {
    "Hello ${otherPerson}"
  }
}

Person p = new Person();
println p.greet("vanchin")
