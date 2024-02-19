//============================================================================
// Name        : ChangPA7.cpp
// Author      : Yun Chung Chang
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================


#include <iostream>
#include <string>
#include <fstream>
#include "Beverage.h"

using namespace std;

int main() {
	int maxAmount = 100;
	Beverage * array [maxAmount];
	int count = 0;

	ifstream infile;
		infile.open("a7data.txt", ios::in);
		if (!infile.is_open()){
			cout << "Error, the file is not open successfully";
			return 1;
		}

		while (!infile.eof()){
			string Line;
			string ID;
			string name;
			string manufacturer;
			double price;
			int sold;
			char onMenu;
			char roast;
			string origin;
			int caffeine;
			char color;
			string flavor;
			int calories;
			char caffeinated;
			char filtered;
			char spring;
			int percent;
			char blend;


			getline(infile, Line);
			ID = Line.substr(0, 4);
			name = Line.substr(4, 20);
			manufacturer = Line.substr(24, 15);
			price = stod(Line.substr(39, 5));
			sold = stoi(Line.substr(44, 4));
			onMenu = Line.substr(48, 1)[0];

			if (ID[0] == 'C'){
				roast = Line.substr(49, 1)[0];
				origin = Line.substr(50, 10);
				caffeine = stoi(Line.substr(60, 3));
				Coffee * c = new Coffee(ID, name, manufacturer, price,
						sold, onMenu, roast, origin, caffeine);
				array[count] = c;
				count++;
			} else if (ID[0] == 'T') {
				color = Line.substr(49, 1)[0];
				caffeine = stoi(Line.substr(50, 3));
				flavor = Line.substr(53, 10);
				Tea * t = new Tea(ID, name, manufacturer, price,
						sold, onMenu, color, caffeine, flavor);
				array[count] = t;
				count++;
			} else if (ID[0] == 'S'){
				flavor = Line.substr(49, 10);
				calories = stoi(Line.substr(59, 3));
				caffeinated = Line.substr(62, 1)[0];
				Soda * s = new Soda(ID, name, manufacturer, price,
						sold, onMenu, flavor, calories, caffeinated);
				array[count] = s;
				count++;
			} else if (ID[0] == 'W'){
				filtered = Line.substr(49, 1)[0];
				spring = Line.substr(50, 1)[0];
				Water * w = new Water(ID, name, manufacturer, price,
						sold, onMenu, filtered, spring);
				array[count] = w;
				count++;

			} else if (ID[0] == 'J') {
				flavor = Line.substr(49, 10);
				calories = stoi(Line.substr(59, 3));
				percent = stoi(Line.substr(62, 3));
				blend = Line.substr(65, 1)[0];
				Juice * j = new Juice(ID, name, manufacturer, price,
						sold, onMenu, flavor, calories, percent, blend);
				array[count] = j;
				count++;
			}

		}

		infile.close();

		cout << "All Beverages: " << endl;
		for (int i = 0; i < count; i++){
			array[i] -> display();
		}
		cout << endl << endl;


		string input;
		int index;

		cout << "Please enter an ID: " << endl;
		getline(cin, input);

		while (input != "0000"){
			bool found = false;
			for (int i = 0; i < count; i++){
				if (input == (array[i]-> getID())){
					found = true;
					index = i;
				}
			}

			if (found == true){
				array[index] -> display();
				array[index] -> salesCheck();
			} else {
				cout << "Sorry, the ID you inserted doesn't exist. please try again" << endl;
			}

			cout << "Please enter an ID: " << endl;
			getline(cin, input);
		}

		cout << "Thank you very much, here is the final status for all beverages" << endl;
		for (int i = 0; i < count; i++){
			array[i] -> display();
		}

		for (int i = 0; i < count; i++){
			delete array[i];
		}

	return 0;
}
