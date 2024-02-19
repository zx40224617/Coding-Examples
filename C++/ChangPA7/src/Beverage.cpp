/*
 * Beverage.cpp
 *
 *  Created on: May 3, 2023
 *      Author: phoebe.wang
 */

#include "Beverage.h"

// Beverage:
Beverage::Beverage(string ID, string name, string manufacturer,
		double price, int sold, char onMenu) {
	this -> ID = ID;
	this -> name = name;
	this -> manufacturer = manufacturer;
	this -> price = price;
	this -> sold = sold;
	this -> onMenu = onMenu;
}

Beverage::~Beverage() {
	//nothing
}

string Beverage::getID(){
	return ID;
}

void Beverage::display(){
	cout << "ID: " << ID << " name: " << name << " manufacturer: " <<
			manufacturer << " price: " << price << " units sold: " <<
			sold << " On Menu: " << onMenu << " ";
}

void Beverage::salesCheck(){
 //nothing here;
}

// Coffee:
Coffee::Coffee(string ID, string name, string manufacturer,
		double price, int sold, char onMenu, char roast,
		string origin, int caffeine) : Beverage (ID,
		name, manufacturer, price, sold, onMenu){
	this -> ID = ID;
	this -> name = name;
	this -> manufacturer = manufacturer;
	this -> price = price;
	this -> sold = sold;
	this -> onMenu = onMenu;
	this -> roast = roast;
	this -> origin = origin;
	this -> caffeine = caffeine;

}

Coffee::~Coffee() {
	cout << "Coffee deleted" << endl;
}

void Coffee::display(){
	Beverage::display();
	cout << "roast: " << roast << " origin: " << origin <<
			" caffeine: " << caffeine << endl;

}

void Coffee::salesCheck(){
	if (sold < 450 && roast != 'd'){
		onMenu = 'n';
		cout << "The sales in not enough (450) to keep this product on menu." << endl;
		cout << "The status has changed to \"not on menu\"(n)"<< endl;
	}
}

// Tea:
Tea::Tea(string ID, string name, string manufacturer,
		double price, int sold, char onMenu, char color,
		int caffeine, string flavor) : Beverage (ID,
		name, manufacturer, price, sold, onMenu){
	this -> ID = ID;
	this -> name = name;
	this -> manufacturer = manufacturer;
	this -> price = price;
	this -> sold = sold;
	this -> onMenu = onMenu;
	this -> color = color;
	this -> caffeine = caffeine;
	this -> flavor = flavor;

}

Tea::~Tea() {
	cout << "Tea deleted" << endl;
}

void Tea::display(){
	Beverage::display();
	cout << "color: " << color << " caffeine: " << caffeine <<
			" flavor: " << flavor  << endl;
}

void Tea::salesCheck(){
	if (sold < 700 && color != 'g'){
		onMenu = 'n';
		cout << "The sales in not enough (700) to keep this product on menu." << endl;
		cout << "The status has changed to \"not on menu\"(n)"<< endl;
	}
}


// soda:
Soda::Soda(string ID, string name, string manufacturer,
		double price, int sold, char onMenu, string flavor,
		int calories, char caffeinated) : Beverage (ID,
		name, manufacturer, price, sold, onMenu){
	this -> ID = ID;
	this -> name = name;
	this -> manufacturer = manufacturer;
	this -> price = price;
	this -> sold = sold;
	this -> onMenu = onMenu;
	this -> flavor = flavor;
	this -> calories = calories;
	this -> caffeinated = caffeinated;

}

Soda::~Soda() {
	cout << "Soda deleted" << endl;
}

void Soda::display(){
	Beverage::display();
	cout << "flavor: " << flavor << " calories: " << calories <<
			" caffeinated: " << caffeinated << endl;
}

void Soda::salesCheck(){
	if (sold < 1000){
		onMenu = 'n';
		cout << "The sales in not enough (1000) to keep this product on menu." << endl;
		cout << "The status has changed to \"not on menu\"(n)"<< endl;
	}
}


// water:
Water::Water(string ID, string name, string manufacturer,
		double price, int sold, char onMenu, char filtered,
		char spring) : Beverage (ID,
		name, manufacturer, price, sold, onMenu){
	this -> ID = ID;
	this -> name = name;
	this -> manufacturer = manufacturer;
	this -> price = price;
	this -> sold = sold;
	this -> onMenu = onMenu;
	this -> filtered = filtered;
	this -> spring = spring;

}

Water::~Water() {
	cout << "Water deleted" << endl;
}

void Water::display(){
	Beverage::display();
	cout << "filtered: " << filtered << " spring: " << spring << endl;
}

void Water::salesCheck(){
	if (sold < 300 && spring != 'y'){
		onMenu = 'n';
		cout << "The sales in not enough (300) to keep this product on menu." << endl;
		cout << "The status has changed to \"not on menu\"(n)"<< endl;
	}

}



// juice:
Juice::Juice(string ID, string name, string manufacturer,
		double price, int sold, char onMenu, string flavor,
		int calories, int percent, char blend) : Beverage (ID,
		name, manufacturer, price, sold, onMenu){
	this -> ID = ID;
	this -> name = name;
	this -> manufacturer = manufacturer;
	this -> price = price;
	this -> sold = sold;
	this -> onMenu = onMenu;
	this -> flavor = flavor;
	this -> calories = calories;
	this -> percent = percent;
	this -> blend = blend;

}

Juice::~Juice() {
	cout << "Juice deleted" << endl;
}

void Juice::display(){
	Beverage::display();
	cout << "flavor: " << flavor << " calories: " << calories <<
				" percent juice: " << percent << " juice blend: " <<
				blend << endl;
}

void Juice::salesCheck(){
	if (sold < 100 && percent != 100){
		onMenu = 'n';
		cout << "The sales in not enough (100) to keep this product on menu." << endl;
		cout << "The status has changed to \"not on menu\"(n)"<< endl;
	}

}


