#include <iostream>

using namespace std;

int main () {
  int myarray [7] = {
    12, 23,45,66,77,80,100
  };

  for (int c=0; c < sizeof(myarray)/sizeof(myarray[0]); c++) {
    cout << "myarray [" << c << "] = " << myarray[c] << endl;
  }

  myarray [3] = 333;
  cout << "modified array is " << endl;
  for (unsigned int a=0; a < sizeof(myarray) / sizeof (myarray[a]); a++) {
    cout << "myarray [" << a << "] = " << myarray[a] << endl;
  }

  cout << "myarray " << sizeof(myarray) << endl;
  cout << "myarray actual " << sizeof(myarray[0]) << endl;
  cout << "myarray lenght " << sizeof(myarray)/sizeof(myarray[0]) << endl;
  return 0;
}
