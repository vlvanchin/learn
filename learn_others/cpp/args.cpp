#include <iostream>

using namespace std;

int getnum();
int findmax(int num1, int num2);

int main () {
	int n1 = getnum();
	int n2 = getnum();
	int max = findmax(n1, n2);
	
	cout << "max number is " << max << endl;
	return 0;
}

int getnum() {
	int num;
	
	cout << "enter a number ";
	cin >> num;
	return num;
}

int findmax(int num1, int num2) {
	return (num1 < num2) ? num2 : num1;
}
