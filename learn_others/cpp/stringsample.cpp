#include <string>
#include <iostream>

using namespace std;

int main () {
  string str1;
  
  cout << "enter a string" << endl;
  getline(cin, str1);

  cout << "input string is " << str1 << endl;
  cout << "length of the string " << str1.size() << endl;
  
  cout << "is the string empty? " << endl;
  if (str1.empty()) {
    cout << "string is empty" << endl;
  } else {
    cout << "string is full" << endl;
  }
  
  str1 = "";
  cout << "length of str1 " << str1.size() << endl;
  
  cout << "is the string empty? " << endl;
  if (str1.empty()) {
    cout << "string is empty" << endl;
  } else {
    cout << "string is full" << endl;
  }
  return 0;
}