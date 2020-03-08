#include <iostream>
#include <string>

using namespace std;

class Dog {
	public:
		void setAge (int age);
		void setName (string name);
		void setColor (string color);
		int getAge();
		string getName();
		string getColor();
		void bark();
	private:
		int age;
		string name;
		string color;
}fido;

void Dog::setAge(int age) {
	this -> age = age;
}
void Dog::setName(string name) {
	this -> name = name;
}
void Dog::setColor (string color) {
	this -> color = color;
}

int Dog::getAge() { return this->age; }
string Dog::getName() { return this->name; }
string Dog::getColor() { return this->color; }

void Dog::bark() {
	cout << "Wooof!" << endl;
}

int main() {
	fido.setName("Fido");
	Dog tiger;
	tiger.setName("Tiger");
	cout << "Dog name is " << fido.getName() << endl;
	cout << "Dogs only ";
	fido.bark();
	cout << "another dog's name is " << tiger.getName() << endl;
	return 0;
}
