/*
 * HashTable.h
 *
 *  Created on: Oct 16, 2022
 *      Author: phoebe.wang
 */

#include <iostream>
using namespace std;
#include "LinkedList.h"

#ifndef HASHTABLE_H_
#define HASHTABLE_H_

struct EmployeeData{
	string ID = "000000000";
	string name = "";
};

struct TableEntry{
	EmployeeData person;
	LL <EmployeeData> linkedList;

};

int hashFunction(string ID){
	int num = 0;
	for (int i = 0; i < ID.length(); i++){
		num += ID.at(i);
	}

	if (num >= 530){
		num = num * 2;
	} else {
		num = num * 3;
	}

	return num % 317;
}

class HashTable{
	private:
		TableEntry hashTable[320];
		int numElements;
	public:
		HashTable();
		void test();
		void hash(int, string, string);
		void search(string);
		void print_hashTable();
};


HashTable :: HashTable(){
	EmployeeData E;
	TableEntry T;
	T.person = E;
	for (int i = 0; i < 320; i++){
		hashTable[i] = T;
	}
	numElements = 0;
}

void HashTable :: test(){
	int nohash = 0;
	int onehash = 0;
	int morehash = 0;
	double sumChain = 0;

	for (int i = 0; i < 320; i++){
		if (hashTable[i].person.ID == "000000000"){
			nohash++;
		} else if (hashTable[i].linkedList.list_length() == 0){
			onehash++;
		} else {
			sumChain += hashTable[i].linkedList.list_length();
			morehash++;
		}
	}

	cout << "number of elements with 0 hashes " << nohash << endl;
	cout << "number of elements with 1 hashes " << onehash << endl;
	cout << "number of elements with >1 hashes " << morehash << endl;
	cout << "average chain size for elements with >1 hashes " << sumChain / morehash << endl;
}

void HashTable :: hash(int num, string name, string ID){
	EmployeeData temp;
	temp.ID = ID;
	temp.name = name;

	if (hashTable[num].person.ID == "000000000" ){
		hashTable[num].person = temp;
	} else {
		hashTable[num].linkedList.push_front(temp);
	}
}

void HashTable :: print_hashTable(){
	for (int i = 0; i < 320; i++){
		cout << "hash table entry " << i;
		if (hashTable[i].person.ID == "000000000"){
			cout << "  no data" << endl;
		} else {
			cout << "  employee " << hashTable[i].person.ID <<
					" name " << hashTable[i].person.name;
		}

		hashTable[i].linkedList.display_list();
	}
}

void HashTable :: search(string ID){
	int entry = hashFunction(ID);
	if (hashTable[entry].person.ID == "000000000"){
		cout << "employee not found" << endl;
	} else if (hashTable[entry].person.ID == ID){
		cout << "employee " << ID << " name " << hashTable[entry].person.name;
	} else if (hashTable[entry].linkedList.list_length() > 0){
		hashTable[entry].linkedList.searchID(ID);
	} else {
		cout << "employee not found" << endl;
	}
}




#endif /* HASHTABLE_H_ */

