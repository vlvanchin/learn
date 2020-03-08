#include <iostream>

int count;
extern void write_exten();

int main () {
	count = 5;
	write_exten();
	return 0;
}
