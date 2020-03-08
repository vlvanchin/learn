#include <string>
#include <iostream>

using namespace std;

int main (){
	
	string str = "sree bagvath and sree subbash and susila";
	string sub1 = "sree";
	string sub2 = "van";
	
	int pos = str.find(sub1, 0);
	if (pos != string::npos) {
		cout << sub1 << " found at " << pos << endl;
	} else {
		cout << sub1 << " not found" << endl;
	}
	
	pos = str.find(sub2, 0);
	if (pos != string::npos) {
		cout << sub2 << " found at " << pos << endl;
	} else {
		cout << sub2 << " not found" << endl;
	}	
	
	pos = str.find_first_of("la");
	cout << "find_first_of \"la\" is " << pos << endl;
	
	pos = str.find_first_not_of ("la");
	cout << "find_first_not_of \"la\" is " << pos << endl;
	
	pos = str.find_last_of("la");
	cout << "find_last_of \"la\" is " << pos << endl;
	
	pos = str.find_last_not_of ("la");
	cout << "find_last_not_of \"la\" is " << pos << endl;
	
	cout << "inserting \"vanchin\" at position 10" << endl;
	cout << "original string" << endl;
	cout << str;
	str.insert(10, "vanchin");
	cout << "after insert" << endl;
	cout << str;
	
	return 0;
}