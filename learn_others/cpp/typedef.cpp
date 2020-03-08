#include<iostream>
using namespace std;

typedef unsigned short int USHRT;

int main() {
  USHRT width;
  USHRT height;

  cout << "enter width: "; cin >> width;
  cout << "enter height: "; cin >> height;

  cout << " area is " << (width * height) << endl;
  return 0;
}
