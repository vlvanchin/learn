#include <iostream>
using namespace std;

void swap(int a, int b);

int main() {
	int x = 2;
	int y = 4;
	
	cout << " x is : " << x << " and y is : " << y << endl;
	swap(x,y);
	cout << " x is : " << x << " and y is : " << y << endl;
	return 0;
}

void swap(int n1, int n2) {
	int temp;
	
	temp = n1;
	n1 = n2;
	n2 = temp;
}
