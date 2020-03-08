#include <iostream>
#include <string>

using namespace std;

int main () {
	string str;
	
	cout << "enter the string" << endl;
	getline (cin, str);
	
	string main = "this is the user input ";
	
	cout << "joined string : " << main.append(str) << endl;
	
	string str2;
	cout << "enter the string to compare: " << endl;
	getline (cin, str2);
	
	if (str == str2)
	  cout << "strings are the same" << endl;
	else 
	  cout << "strings are not the same" << endl;
	  
	if (str.compare(str2) == 0) {
	  cout << "strings are the same" << endl;
	} else {
	  cout << "strings are different" << endl;
	}
	
	string newstring;
	newstring.assign(str2);
	cout << "newstring value is " << newstring << endl;
	return 0;
}