#include <iostream>
using namespace std;

int main () {
  const double PI = 3.14159;
  double radius;

  cout << "enter the radius of the circle" << endl;
  cin >> radius;

  cout << "diameter is " << (radius + radius) << endl;
  cout << "area is PI*r2 " << (PI * ( radius * radius)) << endl;
  cout << "circumference " << ((radius + radius) * PI) << endl;
  return 0;
}
