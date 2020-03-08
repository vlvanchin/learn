#include <iostream>

using namespace std;

int main () {
	int var1;
	char var2[10];

	cout << "address of var1 " << &var1 << endl;
	cout << "address of var2 " << &var2 << endl;

	int *ip;
	var1 = 20;
	
	ip = &var1;
	
	cout << "the address stored at ip is " << ip << endl;
	cout << "the address of &var1 is " << &var1 << endl;
	cout << "the address of &ip is " << &ip << endl;
	cout << "value of *ip is " << *ip << endl;

	return 0;
}
