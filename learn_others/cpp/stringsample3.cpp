#include <iostream>
#include <string>

using namespace std;

int main() {
	string str1 = "String 1 value";
	string str2 = "String 2 value";
	
	cout << "str1 is :" << str1 << endl;
	cout << "str2 is :" << str2 << endl;
	
	str1.swap(str2);
	
	cout << "str1 is :" << str1 << endl;
	cout << "str2 is :" << str2 << endl;
	
	string str = "this is a string for testing";
	cout << str << endl;
	str.erase(9,10);
	cout << "after erase(9,10)" << endl;
	cout << str << endl;
	str.replace(9,2," hello there ");
	cout << str << endl;
	return 0;
}