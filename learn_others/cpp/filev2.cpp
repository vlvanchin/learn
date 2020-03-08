#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main () {
	
	ifstream ifile ("records.txt");
	if (!ifile) {
		cout << "unable to open file" << endl;
		return -1;
	}
	
	string str;
	int i=0, j=0;
	string temp[12];
	while(!ifile.eof()) {
		if ((i + 1) % 3 == 0) {
			getline(ifile, temp[i++]);
		} else {
			getline(ifile,temp[i++], '\t');
		}
	}
	int last = i;
	i=0;
	while(i<last) {
		cout << temp[i++] << endl;
		cout << temp[i++] << endl;
		cout << temp[i++] << endl << endl << endl;
	}
	ifile.close();
	return 0;
}