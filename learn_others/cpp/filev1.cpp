#include<iostream>
#include<string>
#include<fstream>

using namespace std;

int main() {
	string msg = "this is line1 \n";
	msg += "this is line2 \n";
	msg += "this is line3\n";

	ofstream fobject1("myfile.txt");
	
	if (!fobject1) {
		cout << "file not found" << endl;
		return -1; 
	}
	
	fobject1 << msg << endl;
	fobject1.close();
	
	cout << "updating myfile.txt file with some text" << endl;
	
	string update = "some text updated to the";
	update.append(" existing contents \njust see ");
	
	ofstream fobject2("myfile.txt",ios_base::app);
	
	if( !fobject2) {
		cout << "file cannot append " << endl;
		return -1;
	}
	fobject2 << update << endl;
	fobject2.close();
	
	cout << "file contents : " << endl;
	char ch;
	ifstream fobject3("myfile.txt");
	if (! fobject3) {
		cout << "file cannot be read" << endl;
		return -1;
	}
	while(!fobject3.eof()) {
		fobject3.get(ch);
		cout << ch;
	}
	fobject3.close();
	return 0;
}