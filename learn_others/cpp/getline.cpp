#include <iostream>
#include <string>
using namespace std;

int main () {

  string str1, str2, str3;
  cout << "enter str1 ";
  getline (cin, str1);
//   cin >> str1;

  cout << "enter str2 ";
  getline(cin, str2, '\t' );
//  cin >> str2;

  getline(cin, str3);

  cout << "the str1 is :" << str1 << endl;
  cout << "the str2 is :" << str2 << endl;
  cout << "the str3 is :" << str3 << endl;

  return 0;
}
