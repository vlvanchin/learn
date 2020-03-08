#include <iostream>
#include <string>

using namespace std;

void writename();
void writetitle();

int main () {
	writetitle();
	writename();
	return 0;
}

void writename() {
	string name="vanchinathan";
	cout << name << endl;
}

void writetitle() {
	string title = "cpp programming";
	cout << title << " by " ;
}
