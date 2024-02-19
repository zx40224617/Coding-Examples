//============================================================================
// Name        : ChangA6.cpp
// Author      : Yun Chung Chang
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <fstream>
using namespace std;
#include "LinkedList.h"
#include "HashTable.h"





int main() {
	HashTable Table;
	string searchID = "1";

	ifstream inFile;
	string input;
	inFile.open("a6data F22.txt");
	if (inFile.is_open()){
		getline (inFile, input);
		while(!inFile.eof()){
			string ID = input.substr(0, 9);
			string name = input.substr(9, 34);
			int entry = hashFunction(ID);
			Table.hash(entry, name, ID);
			getline(inFile, input);
		}
		inFile.close();
	} else {
		cout << "error" << endl;
	}

	cout << "Testing :" << endl;
	Table.test();
	cout << endl;
	cout << "The full hashTable :" << endl << endl;
	Table.print_hashTable();
	cout << endl;


	while (searchID != "0"){
		string a;		cout << "enter key to search for:" << endl;
		cin >> searchID;
		if (searchID == "0"){
			break;
		}
		Table.search(searchID);

	}

	cout << "thanks and have a great day" << endl;














	return 0;

}
