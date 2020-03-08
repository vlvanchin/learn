#include <iostream>
#include <math.h>
using namespace std;

int main () {

  enum colors {
    BLACK=0,BROWN,RED,ORANGE,YELLOW,GREEN,BLUE,VIOLET,GRAY,WHITE
  };

  int total = ((BROWN * 10) + BLACK + (BLACK*0));
  cout << "BROWN, BLACK and BLACK is " << total << endl;

  total = (((BROWN * 10) + (BLACK * 10)) * pow(10,ORANGE));
  cout << "BROWN, BLACK and ORANGE is " << total << endl;

  return 0;
}
