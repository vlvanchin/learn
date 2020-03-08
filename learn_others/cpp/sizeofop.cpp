#include <iostream>
using namespace std;

int main() {
  float num = 13.2;
  int ints[30];
  char chars [30];

  cout << "size of float " << sizeof(float) << endl;
  cout << "size of int " << sizeof(int) << endl;
  cout << "size of char " << sizeof (char) << endl;

  cout << "size of float variable " << sizeof(num) << endl;
  cout << "size of ints array " << sizeof(ints) << endl;
  cout << "size of chars arary " << sizeof (chars) << endl;

  return 0;
}
