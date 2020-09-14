package  com.test

/** 
 *  a class description
 */
class PersonOther {
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

/*
PersonOther p1 = new PersonOther()
p1.greet ("vanchin")
*/

public static void main (String[] args) {
    PersonOther p1 = new PersonOther()
    p1.greet ("vanchin")
}