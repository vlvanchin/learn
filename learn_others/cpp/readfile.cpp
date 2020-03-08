#include<iostream>
#include <string>
#include <fstream>

using namespace std;

int main() {
	char ch; // to read char by char from file
	ifstream ifile("myfile.txt");
	
	if (!ifile) {
		cout << "myfile.txt not able to open" << endl;
		return -1;
	}
	
	cout << "reading contents of the file " << endl;
	while (! ifile.eof()) {
		ifile.get(ch);
		cout << ch;
	}
	
	return 0;
}