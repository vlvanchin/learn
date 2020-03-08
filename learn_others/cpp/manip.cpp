#include<string>
#include<iostream>
#include<iomanip>

using namespace std;

int main() {

	string str = "this is a msg";
	double flt = 1.034234324;
	int num = 43;
	bool flag = true;
	
	cout.width(20);
	cout << str << endl;
	
	cout.width(30);
	cout.fill('x');
	cout << str << endl;
	
	cout << setw(40) << setfill ('.') << str << endl;
	cout << num << " in hex " << hex << showbase << num << endl;
	cout << boolalpha << flag << endl;
	return 0;
}