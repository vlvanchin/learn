#include <iostream>
using namespace std;

int main() {

  bool flag [3] = {true, false, true};
  double nums[3] = { 1.1, 32.3, 343.0};
  char fnames[4] = {'A', 'a', '.', '\0'};

  cout << flag [2] << endl;
  cout << nums [1] << endl;
  cout << fnames [0] << endl;
  cout << fnames << endl;

  return 0;
}
