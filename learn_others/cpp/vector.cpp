#include <iostream>
#include <vector>
using namespace std;

int main() {
  vector <int> myvector (3,100);
  cout << "vector size is " << myvector.size() << endl;
  for(int c=0; c < myvector.size(); c++) {
    cout << "element at (" << c << ") is " << myvector.at(c) << endl;
  }

  myvector.push_back(9);
  myvector.push_back(10);
  cout << "adding two more elements" << endl;
  for(int c=0; c < myvector.size(); c++) {
    cout << "element at (" << c << ") is " << myvector.at(c) << endl;
  }

  cout << "the last element is :" << myvector.back() << endl;
  // removes the last element
  cout << "now removing the last element" << endl;
  myvector.pop_back();
  cout << "the last element now is :" << myvector.back() << endl;

  cout << "vector elements are : " << endl;
  for(int c=0; c < myvector.size(); c++) {
    cout << "element at (" << c << ") is " << myvector.at(c) << endl;
  }

  // now clearing the vector
  myvector.clear();
  cout << "now the size of the vector is " << myvector.size() << endl;
  cout << "is the vector empty " << myvector.empty() << endl;
  return 0;
}
