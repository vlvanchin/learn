#include <iostream>
#include <string>

using namespace std;

int main() {
	
	string str1 = "Hello string";
	string str2 ("this is string 2");
	string str3;
	
	str3 = str1 + str2;
	
	cout << str1 << endl;
	cout << str2 << endl;
	cout << str3 << endl;
	return 0;
}